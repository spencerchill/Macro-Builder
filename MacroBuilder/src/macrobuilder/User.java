/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

/**
 *
 * @author spencerhill
 */
public class User {

    public enum ActivityLevel {
        NOT_ACTIVE,
        MODERATLY_ACTIVE,
        ACTIVE
    }

    public enum CurrentMode {
        CUT,
        MAINTAIN,
        BULK
    }
    public enum Gender {
        Male,
        Female
    }
    
    private Gender gender;
    private int age;
    //height in inches
    private float height;
    //weight in lb
    private float weight;
    private ActivityLevel activityLevel;
    private CurrentMode curMode;
    private Day day;

    public User(Gender gender, int age, float weight, float height, ActivityLevel activityLevel, CurrentMode curMode) {
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.activityLevel = activityLevel;
        this.curMode = curMode;
        day = new Day(gender, age, weight, height, activityLevel, curMode);
    }

    //Getters for each variable
    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
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
    
    public Day getDay() {
        return day;
    }

    //Setters for each variable
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(float cm) {
        this.height = cm;
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