/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import database.DatabaseUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a day with
 * respective calories goals, protein goals
 * and consumed, calories, fat, carbs, and protein
 * @author spencerhill
 */
public class Day {
    
    private User.Gender gender;
    private int age;
    private float height;
    private float weight;
    private User.ActivityLevel activityLevel;
    private User.CurrentMode mode;
    private int calorieGoal;
    private float fatGoal;
    private float carbGoal;
    private float proteinGoal;
    private int calories;
    private float fat;
    private float carbs;
    private float protein;
    private int remainingCalories;
    private DatabaseUtil databaseUtil;
    /**
     * 6-parameter constructor that sets up a day 
     * with the current values of a user, calculates calorieGoal
     * calculates proteinGoal, and initialized calories and macros to 0
     * @param gender
     * @param age
     * @param height
     * @param weight
     * @param activityLevel
     * @param mode 
     * @param calories 
     * @param fat 
     * @param carbs 
     * @param protein 
     */
    public Day (User.Gender gender, int age, float height, float weight, User.ActivityLevel activityLevel, User.CurrentMode mode, int calories, float fat, float carbs, float protein){
        
        try {
            databaseUtil = new DatabaseUtil();
        } catch (IOException ex) {
            Logger.getLogger(Day.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.mode = mode;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        calcCalories();
        calcProtein();
        calcFat();
        calcCarbs();
    }
    
    /**
     * Method that calculated the days calories goal
     * based off of the users gender, weight, height, age,
     * mode, and activity level
     */
    private void calcCalories() {
        if (gender.equals(User.Gender.MALE)) {
            calorieGoal = (int) (88.362 + (13.397 * (weight/2.205)) + (4.799 * (2.54 * height)) - (5.677 * age));
        }
        if (gender.equals(User.Gender.FEMALE)) {
            calorieGoal = (int) (447.593 + (9.247 * (weight/2.205)) + (3.098 * (2.54 * height)) - (4.330 * age));
        }
        
        switch (mode) {
                case CUT -> {
                    calorieGoal *= .75;
                }
                case MAINTAIN -> {
                    calorieGoal *= 1;
                }
                case BULK -> {
                    calorieGoal *= 1.25;
                }
            }
        
        switch (activityLevel) {
                case ACTIVE -> {
                    calorieGoal += 300;
                }
                case MODERATELY_ACTIVE -> {
                    calorieGoal += 150;
                }
                default -> {
                }
            }
        remainingCalories = calorieGoal;
    }
    
    /**
     * Method that calculated the days protein goal
     * based off of the users gender and weight
     */
    private void calcProtein() {
        proteinGoal = (float) ((weight / 2.205) * 1.8);
    }
    
    /**
     * Method that calculated the days fat goal
     */
    private void calcFat() {
        fatGoal = (float) (getCalorieGoal() * .2) / 9;
    }
    
    /**
     * Method that calculated the days carb goal
     */
    private void calcCarbs() {
        carbGoal = getProteinGoal() - getFatGoal();
    }
    
    /**
     * Method that adds to the days calories, fat, carbs, and protein
     * from given values calories, fat, carbs, protein, and data types being
     * int, float, float, float respectively 
     * @param calories
     * @param fat
     * @param carbs
     * @param protein 
     * @param currDate 
     */
    public void intake (int calories, float fat, float carbs, float protein, java.sql.Date currDate) {
        this.calories += calories;
        this.fat += fat;
        this.carbs += carbs;
        this.protein += protein;
        remainingCalories  -= calories;
        
        try {
            databaseUtil.storeIntake(this.calories, this.fat, this.carbs, this.protein, currDate);
        } catch (SQLException ex) {
            Logger.getLogger(Day.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Method that returns an int 
     * representing the days calorie goal 
     * @return 
     */
    public int getCalorieGoal() {
        return this.calorieGoal;
    }
    
    /**
     * Method that returns a float
     * representing the days Fat goal
     * @return 
     */
    public float getFatGoal() {
        return this.fatGoal;
    }
    
    /**
     * Method that returns a float
     * representing the days carb goal
     * @return 
     */
    public float getCarbGoal() {
        return this.carbGoal;
    }
    
    /**
     * Method that returns a float
     * representing the days protein goal
     * @return 
     */
    public float getProteinGoal() {
        return this.proteinGoal;
    }
    
    /**
     * Method that returns an int representing 
     * the current amount of calories consumed
     * @return 
     */
    public int getCalories() {
        return this.calories;
    }
    
    /**
     * Returns remaining calories.
     * @return 
     */
    public int getRemainingCalories() {
        if(remainingCalories < 0)
        {
            this.remainingCalories = 0;
        }
        return this.remainingCalories;
    }
    /**
     * Method that returns a float representing 
     * the current amount of fat consumed
     * @return 
     */
    public float getFat() {
        return this.fat;
    }
    
    /**
     * Method that returns a float representing 
     * the current amount of carbs consumed
     * @return 
     */
    public float getCarbs() {
        return carbs;
    }
    
    /**
     * Method that returns a float representing 
     * the current amount of protein consumed
     * @return 
     */
    public float getProtein() {
        return protein;
    }       
    
    public void setWeight(float weight){
        this.weight = weight;
    }
            
    public void setRemainingCalories(int calories){
       this.remainingCalories -= calories;
    }
}