/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

/**
 * This class represents a User
 * @author spencerhill
 */
public class User {

    /**
     * public enum variable that defines the 
     * users activity level consisting of three values:
     * NON_ACTIVE, MODERATLY_ACTIVE, ACTIVE
     */
    public enum ActivityLevel {
        NOT_ACTIVE,
        MODERATLY_ACTIVE,
        ACTIVE
    }

    /**
     * public enum variable that defines the 
     * users current mode consisting of three values:
     * CUT, MAINTAIN, BULK
     */
    public enum CurrentMode {
        CUT,
        MAINTAIN,
        BULK
    }
    
    /**
     * public enum variable that defines the 
     * users gender consisting of two values:
     * Male, Female
     */
    public enum Gender {
        Male,
        Female
    }
    
    private String firstName;
    private String username;
    private String password;
    private Gender gender;
    private int age;
    
    //height in inches
    private float height;
    
    //weight in lb
    private float weight;
    private ActivityLevel activityLevel;
    private CurrentMode curMode;
    private Day day;

    /**
     * 9-parameter constructor that initializes a user
     * and initializes an instance of a Day object
     * @param firstName
     * @param username
     * @param password
     * @param gender
     * @param age
     * @param height
     * @param weight
     * @param activityLevel
     * @param curMode 
     */
    public User(String firstName, String username, String password, Gender gender, int age, float height, float weight, ActivityLevel activityLevel, CurrentMode curMode) {
        this.firstName = firstName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.curMode = curMode;
        day = new Day(gender, age, height, weight, activityLevel, curMode);
    }

    /**
     * Method that returns a String representing
     * the first name of the user
     * @return 
     */
    public String getFirstName(){
        return firstName;
    }
      
    /**
     * Method that returns a String representing
     * the username of the user
     * @return 
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Method that returns a String representing
     * the password of the user
     * @return 
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Method that returns a User.Gender enum value
     * representing the gender of the user
     * @return 
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Method that returns an int representing
     * the age of the user
     * @return 
     */
    public int getAge() {
        return age;
    }

    /**
     * Method that returns a float representing
     * the height in inches of the user
     * @return 
     */
    public float getHeight() {
        return height;
    }

    /**
     * Method that returns a float representing
     * the weight in lbs of the user
     * @return 
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Method that returns a User.ActivityLevel enum value
     * representing the ActivityLevel of the user
     * @return 
     */
    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    /**
     * Method that returns a User.CurrentMode enum value
     * representing the ActivityLevel of the user
     * @return 
     */
    public CurrentMode getCurrentMode() {
        return curMode;
    }
    
    /**
     * Method that returns a Day Object 
     * representing the day the user is on 
     * @return 
     */
    public Day getDay() {
        return day;
    }

  
    /**
     * Method that sets the firstName of the user
     * to a given String 
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method that sets the gender of the user
     * to a given User.Gender enum value
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Method that sets the age of the user
     * to a given int value
     * @param age 
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method that sets the height in inches of the user
     * to a given float value
     * @param inches
     */
    public void setHeight(float inches) {
        this.height = inches;
    }

    /**
     * Method that sets the weight in lbs of the user
     * to a given float value
     * @param lbs 
     */
    public void setWeight(float lbs) {
        this.weight = lbs;
    }

    /**
     * Method that sets the activity level of the user
     * to a given User.ActivityLevel enum value
     * @param activityLevel
     */
    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    /**
     * Method that sets the current mode of the user
     * to a given User.CurrentMode enum value
     * @param curMode
     */
    public void setCurrentMode(CurrentMode curMode) {
        this.curMode = curMode;
    }
}