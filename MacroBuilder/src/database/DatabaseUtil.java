/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.sql.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Calendar;
import objects.Day;
import objects.Food;
import objects.Meal;
import objects.User;

/**
 * Class that handles database connection and password hashing.
 * @author KingJ
 */
public class DatabaseUtil {
    
    private static String url;
    private static String username;
    private static String password;

    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    /**
     * Reads database credentials from config file.
     * @throws IOException if error reading file.
     */
    public DatabaseUtil() throws IOException {
         //secure database credentials.
        ConfigReader configReader = new ConfigReader("config.properties");
        url = configReader.getUrl();
        username = configReader.getUsername();
        password = configReader.getPassword();
    }
    /**
     * Gets connection to server.
     * @return Connection object.
     * @throws SQLException.
     */
    public  Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Hashes password with salt using PBKDF2 algorithm with HMAC SHA-256.
     * @param password
     * @param salt
     * @return hashed password
     */
    public static String hashPassword(String password, String salt) {
        // Expects array of characters and bytes
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = Base64.getDecoder().decode(salt);

        KeySpec spec = new PBEKeySpec(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);
        try {

            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generates random salt.
     * @return random salt.
     */
    public static String generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    
    /**
     *  Checks if stored password equals entered password.
     * @param username
     * @param password
     * @return boolean representing succesful login.
     * @throws SQLException 
     */
    public boolean loginUser(String username, String password) throws SQLException {
        try ( Connection connection =  DriverManager.getConnection(url, this.username, this.password)) {
            String query = "SELECT password, salt FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    String salt = resultSet.getString("salt");
                    String inputPassword = hashPassword(password, salt);
                    
                    return storedPassword.equals(inputPassword);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Checks if it's the users first time logging in.
     * @param username
     * @return boolean value representning first login.
     * @throws SQLException 
     */
    public boolean firstLogin(String username) throws SQLException {
        try ( Connection connection = DriverManager.getConnection(url, this.username, password)) {
            String query = "SELECT * FROM user_details WHERE user_id = (SELECT id FROM users WHERE username = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            try ( ResultSet resultSet = statement.executeQuery()) {
                //returns true 
                return !resultSet.next();
            }
        }
    }
    
     /**
     *  Inserts username and hashed password into database.
     * @param username
     * @param password
     * @throws SQLException 
     */
    public Boolean registerUser(String username, String password) throws SQLException{
        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);
        
        try (Connection connection =DriverManager.getConnection(url, this.username, this.password)){
            String query = "INSERT INTO users (username, password, salt) VALUES (?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, hashedPassword);
                statement.setString(3, salt);
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
    }
    
    /**
       * Stores user details into database.
       * @param gender
       * @param age
       * @param height
       * @param weight
       * @param activityLevel
       * @param mode
       * @throws SQLException if database error.
       */
      public void storeUserDetails(User.Gender gender, int age, float height, float weight, User.ActivityLevel activityLevel, User.CurrentMode mode) throws SQLException {
          try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "INSERT INTO user_details (user_id, gender, age, height, weight, activity_level, current_mode) VALUES (?, ?, ?, ?, ?, ?, ?)";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setString(2, gender.name());
              statement.setInt(3, age);
              statement.setFloat(4, height);
              statement.setFloat(5, weight);
              statement.setString(6, activityLevel.name());
              statement.setString(7, mode.name());
              statement.executeUpdate();
          }
      }
      
       /**
     * Creates User object from user details stored in database.
     * @return User object.
     * @throws SQLException 
     */
    public User getUserDetails() throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM user_details WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, UserManager.getInstance().getUserId());
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    User.Gender gender = User.Gender.valueOf(resultSet.getString("gender"));
                    int age = resultSet.getInt("age");
                    float height = resultSet.getFloat("height");
                    float weight = resultSet.getFloat("weight");
                    User.ActivityLevel activityLevel = User.ActivityLevel.valueOf(resultSet.getString("activity_level"));
                    User.CurrentMode mode = User.CurrentMode.valueOf(resultSet.getString("current_mode"));
                    
                    return new User(UserManager.getInstance().getUsername(), gender, age, height, weight, activityLevel, mode);
                }
            }
        }
        return null;
    }
    
        public void storeIntake(int calories, float fat, float carbs, float protein, Date date) throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, username, password)){
        String query = "UPDATE days SET calories_consumed = ?, fat_consumed = ?, carbs_consumed = ?, protein_consumed = ? WHERE user_id = ? AND date_day = ?";
        UserManager userManager = UserManager.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, calories);
            statement.setFloat(2, fat);
            statement.setFloat(3, carbs);
            statement.setFloat(4, protein);
            statement.setInt(5, userManager.getUserId());
            statement.setDate(6, date);
            statement.executeUpdate();
        }
    }catch (SQLException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  
    public HashMap <Date, Day> loadDays(HashMap<Date, Day> calendar, User user) throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            UserManager userManager = UserManager.getInstance();
            String query = "SELECT * FROM days WHERE user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userManager.getUserId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
            java.sql.Date date = resultSet.getDate("date_day");
            float weight = resultSet.getFloat("weight");
            
            User.ActivityLevel activityLevel = User.ActivityLevel.valueOf(resultSet.getString("activity_level"));
            User.CurrentMode mode = User.CurrentMode.valueOf(resultSet.getString("current_mode"));
            //im gonna copy paste this code later when we read from days :)))))))))
//            int calorieGoal = resultSet.getInt("CalorieGoal");
//            float fatGoal = resultSet.getFloat("FatGoal");
//            float carbGoal = resultSet.getFloat("CarbGoal");
//            float proteinGoal = resultSet.getFloat("ProteinGoal");
            int calories = resultSet.getInt("calories_consumed");
            float fat = resultSet.getFloat("fat_consumed");
            float carbs = resultSet.getFloat("carbs_consumed");
            float protein = resultSet.getFloat("protein_consumed");

            Day day = new Day(user.getGender(), user.getAge(), user.getHeight(), weight, activityLevel, mode, calories, fat, carbs, protein);
            calendar.put(date, day);
            }
        }
        return calendar;
    }
    
    
    public void changeActivityLevel(User.ActivityLevel newLevel, Date date){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
              UserManager userManager = UserManager.getInstance();
              //inserting into user details table
              String updateMode = "UPDATE user_details SET activity_level  = ? WHERE user_id = ?";
              try(PreparedStatement statement = connection.prepareStatement(updateMode)){
                  statement.setString(1, newLevel.name());
                  statement.setInt(2, userManager.getUserId());
                  statement.executeUpdate();
              }
              String updateDayMode = "UPDATE days SET activity_level = ? WHERE date_day = ?";
              try(PreparedStatement statement = connection.prepareStatement(updateDayMode)){
                  statement.setString(1, newLevel.name());
                  statement.setDate(2, date);
                  statement.executeUpdate();
              }
          }catch (SQLException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    /**
     *  Updates day and user_details table with the new mode selected
     * @param newMode
     * @param date 
     */
    public void changeMode(User.CurrentMode newMode, Date date){
          try(Connection connection = DriverManager.getConnection(url, username, password)){
              UserManager userManager = UserManager.getInstance();
              //inserting into user details table
              String updateMode = "UPDATE user_details SET current_mode = ? WHERE user_id = ?";
              try(PreparedStatement statement = connection.prepareStatement(updateMode)){
                  statement.setString(1, newMode.name());
                  statement.setInt(2, userManager.getUserId());
                  statement.executeUpdate();
              }
              String updateDayMode = "UPDATE days SET current_mode = ? WHERE date_day = ?";
              try(PreparedStatement statement = connection.prepareStatement(updateDayMode)){
                  statement.setString(1, newMode.name());
                  statement.setDate(2, date);
                  statement.executeUpdate();
              }
          }catch (SQLException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     *  Enters weight into day and user_details table
     * @param weight
     * @param date
     * @throws SQLException 
     */
    public void checkIn(float weight, Date date) throws SQLException{
         try(Connection connection = DriverManager.getConnection(url, username, password)) {
             UserManager userManager = UserManager.getInstance();
             String userUpdate = "UPDATE user_details SET weight = ? WHERE user_id = ?";
             try (PreparedStatement statement = connection.prepareStatement(userUpdate)) {
                 statement.setFloat(1, weight);
                 statement.setInt(2, userManager.getUserId());
                 statement.executeUpdate();
             }
             // if you do calender.getDate you should have the date parameter for current day
             String updateDay = "UPDATE days SET weight = ? WHERE date_day = ?";
             try(PreparedStatement statement = connection.prepareStatement(updateDay)){
             statement.setFloat(1, weight);
             statement.setDate(2, date);
             statement.executeUpdate();
         }
         }catch (SQLException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Creation of day object. Used when user logs in first time of day.
     * @param user
     * @param date
     * @throws SQLException 
     */
    public void addDay(User user, Date date) throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            System.out.println(date);
          String query = "INSERT INTO days (user_id, date_day, weight, activity_level, current_mode) VALUES (?, ?, ?, ?, ?)";
          UserManager userManager = UserManager.getInstance();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userManager.getUserId());
            statement.setDate(2, date);
            statement.setFloat(3,  user.getWeight());
            statement.setString(4, user.getActivityLevel().toString()); // Assuming ActivityLevel and Mode are enums
            statement.setString(5, user.getModeAsString());
            statement.executeUpdate();
        }catch (SQLException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     *  Gets userId corresponding to the username.
     * @param username
     * @return userId. 
     * @throws SQLException 
     */
    public int getUserId(String username) throws SQLException {
        try ( Connection connection = DriverManager.getConnection(url, this.username, password)) {
            String query = "SELECT id FROM users WHERE username = ?";
            try ( PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try ( ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        }
        return -1;
    }
    
    /**
     * Stores a food item into the database
     * @param food
     * @throws SQLException 
     */
    public void storeFood(Food food) throws SQLException {
        String name = food.getName();
        int calories = food.getCalories();
        float fat = food.getFat();
        float carbs = food.getCarbs();
        float protein = food.getProtein();
        
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "INSERT INTO foods (user_id, food_name, calories, fat, carbs, protein) VALUES (?, ?, ?, ?, ?, ?)";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setString(2, name);
              statement.setInt(3, calories);
              statement.setFloat(4, fat);
              statement.setFloat(5, carbs);
              statement.setFloat(6, protein);
              statement.executeUpdate();
          }
    }
    
    /**
     * deletes a food item from the database
     * @param food
     * @throws SQLException 
     */
    public void deleteFood(Food food) throws SQLException {
        int foodID = getFoodID(food);
        
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "DELETE FROM foods where user_id = ? and food_id = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setInt(2, foodID);
              statement.executeUpdate();
          }
    }
    
    /**
     * retrieves a food item's ID in from databse
     * @param food
     * @return
     * @throws SQLException 
     */
    public int getFoodID(Food food) throws SQLException {
        String name = food.getName();
        int calories = food.getCalories();
        float fat = food.getFat();
        float carbs = food.getCarbs();
        float protein = food.getProtein();
        
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "Select food_id FROM foods where user_id = ? and food_name = ? and calories = ? and fat = ? and carbs = ? and protein = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setString(2, name);
              statement.setInt(3, calories);
              statement.setFloat(4, fat);
              statement.setFloat(5, carbs);
              statement.setFloat(6, protein);
              try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    return resultSet.getInt("food_id");
                }
          }
        }
        return -1;
    }
    
    /**
     * returns an array list of foods a user has from the database
     * @return
     * @throws SQLException 
     */
    public ArrayList<Food> getFoods() throws SQLException {
        
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "Select * from foods where user_id = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              ArrayList<Food> list = new ArrayList<Food>();
              String name;
              int calories;
              float fat;
              float carbs;
              float protein;
              
              try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    name = resultSet.getString("food_name");
                    calories = resultSet.getInt("calories");
                    fat = resultSet.getFloat("fat");
                    carbs = resultSet.getFloat("carbs");
                    protein = resultSet.getFloat("protein");
                    Food food = new Food(name, calories, fat, carbs, protein);
                    list.add(food);
                }
                return list;
            }
        }
    }
    
    /**
     * stroes a meal item into the database
     * @param meal
     * @throws SQLException 
     */
    public void storeMeal(Meal meal) throws SQLException {
        String name = meal.getName();
        int calories = meal.getTotalCalories();
        float fat = meal.getTotalFat();
        float carbs = meal.getTotalCarbs();
        float protein = meal.getTotalProtein();
        
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "INSERT INTO meals (user_id, meal_name, calories, fat, carbs, protein) VALUES (?, ?, ?, ?, ?, ?)";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setString(2, name);
              statement.setInt(3, calories);
              statement.setFloat(4, fat);
              statement.setFloat(5, carbs);
              statement.setFloat(6, protein);
              statement.executeUpdate();
          }
        
        for (int i = 0; i < meal.getFoods().size(); i++) {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              String query = "INSERT INTO meal_foods (meal_id, food_id) VALUES (?, ?)";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, getMealID(meal));
              statement.setInt(2, getFoodID(meal.getFoods().get(i)));
              statement.executeUpdate();
          }
        }
    }
    
    /**
     * returns a meal item's ID from the database
     * @param meal
     * @return
     * @throws SQLException 
     */
    public int getMealID(Meal meal) throws SQLException {
        String name = meal.getName();
        int calories = meal.getTotalCalories();
        float fat = meal.getTotalFat();
        float carbs = meal.getTotalCarbs();
        float protein = meal.getTotalProtein();
        
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "Select meal_id FROM meals where user_id = ? and meal_name = ? and calories = ? and fat = ? and carbs = ? and protein = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setString(2, name);
              statement.setInt(3, calories);
              statement.setFloat(4, fat);
              statement.setFloat(5, carbs);
              statement.setFloat(6, protein);
              try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    return resultSet.getInt("meal_id");
                }
          }
        }
        return -1;
    }
    
    /**
     * deletes a meal item from the database
     * @param meal
     * @throws SQLException 
     */
    public void deleteMeal(Meal meal) throws SQLException {
        int mealID = getMealID(meal);
        
        ArrayList<Integer> mealFoodsIDs = getMealFoodsIDs(meal);
        for (int i = 0; i < mealFoodsIDs.size(); i++) {
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              String query = "DELETE FROM meal_foods where meal_foods_id = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, mealFoodsIDs.get(i));
              statement.executeUpdate();
          }
        }
        
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "DELETE FROM meals where user_id = ? and meal_id = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setInt(2, mealID);
              statement.executeUpdate();
          }
    }
    
    /**
     * returns an array list of mealFoodsIDs from the database
     * @param meal
     * @return
     * @throws SQLException 
     */
    public ArrayList<Integer> getMealFoodsIDs(Meal meal) throws SQLException {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        int mealID = getMealID(meal);
        int foodID;
        
        for (int i = 0; i < meal.getFoods().size(); i++) {
            foodID = getFoodID(meal.getFoods().get(i));
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              String query = "Select meal_foods_id FROM meal_foods where meal_id = ? and food_id = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, mealID);
              statement.setInt(2, foodID);
              try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    ids.add(resultSet.getInt("meal_foods_id"));
                }
          }
        }
        }
        return ids;
    }
    
    /**
     * returns an arraylist of meal items that a user has from the database
     * @return
     * @throws SQLException 
     */
    public ArrayList<Meal> getMeals() throws SQLException{
        ArrayList<Meal> meals = new ArrayList<Meal>();
        
        ArrayList<Integer> mealIDs = new ArrayList<Integer>();
        
        String name;
        int calories;
        float fat;
        float carbs;
        float protein;
        
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "Select meal_id from meals where user_id = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              
              try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    mealIDs.add(resultSet.getInt("meal_id"));
                }
            }
        }
        
        for (int i = 0; i < mealIDs.size(); i++) {
            Meal meal = new Meal();
            
            try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "Select meal_name from meals where user_id = ? and meal_id = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setInt(2, mealIDs.get(i));
              
              try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    meal.setName(resultSet.getString("meal_name"));
                }
            }
        }
            
            
            ArrayList<Integer> foodIDs = new ArrayList<Integer>();
            
            try(Connection connection = DriverManager.getConnection(url, username, password)) {
              String query = "Select food_id from meal_foods where meal_id = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, mealIDs.get(i));
              
              try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    foodIDs.add(resultSet.getInt("food_id"));
                }
            }
        }
            for (int j = 0; j < foodIDs.size(); j++) {
            try(Connection connection = DriverManager.getConnection(url, username, password)) {
              UserManager userManager = UserManager.getInstance();
              String query = "Select * from foods where user_id = ? and food_id = ?";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setInt(2, foodIDs.get(j));
              
              try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    name = resultSet.getString("food_name");
                    calories = resultSet.getInt("calories");
                    fat = resultSet.getFloat("fat");
                    carbs = resultSet.getFloat("carbs");
                    protein = resultSet.getFloat("protein");
                    Food food = new Food(name, calories, fat, carbs, protein);
                    meal.addFood(food);
                }
            }
        }
            }
            meals.add(meal);
        }
        return meals;
    }
}