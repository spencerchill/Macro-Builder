/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuiulder;

/**
 *
 * @author sweet
 */
public class User {

    public enum ActivityLevel {
        NOT_ACTIVE,
        MODERATLY_ACTIVE,
        ACTIVE
    }

    public enum CurrentMode {
        LOSE_WEIGHT,
        MAINTAIN,
        GAIN_WEIGHT
    }
    // Can add username, password, email later
    private String gender;
    private int age;
    //Seperate measurements for height
    private int feet;
    private int inches;
    private float weight;
    private ActivityLevel activityLevel;
    private CurrentMode curMode;

    public User(String gender, int age, int feet, float weight, int inches, ActivityLevel activityLevel, CurrentMode curMode) {
        this.gender = gender;
        this.feet = feet;
        this.inches = inches;
        this.weight = weight;
        this.age = age;
        this.activityLevel = activityLevel;
        this.curMode = curMode;
    }

    //Getters for each variable
    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getFeet() {
        return feet;
    }

    public int getInches() {
        return inches;
    }

    public float getWeight() {
        return weight;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public CurrentMode getCurrentMode() {
        return curMode;
    }

    //Setters for each variable
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }

    public void setInches(int inches) {
        this.inches = inches;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setCurrentMode(CurrentMode curMode) {
        this.curMode = curMode;
    }
}
