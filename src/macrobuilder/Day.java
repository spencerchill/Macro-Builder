/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

/**
 *
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
    private float proteinGoal;
    private int calories;
    private float fat;
    private float carbs;
    private float protein;
    
    public Day (User.Gender gender, int age, float weight, float height, User.ActivityLevel activityLevel, User.CurrentMode mode){
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.mode = mode;
        calcCalories();
        calcProtein();
        calories = 0;
        fat = 0;
        carbs = 0;
        protein = 0;
    }
    
    private void calcCalories() {
        if (gender.equals(User.Gender.Male)) {
            calorieGoal = (int) (88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age));
        }
        if (gender.equals(User.Gender.Female)) {
            calorieGoal = (int) (447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age));
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
                    calorieGoal += 600;
                }
                case MODERATLY_ACTIVE -> {
                    calorieGoal += 300;
                }
                default -> {
                }
            }
    }
    
    private void calcProtein() {
        if (gender.equals(User.Gender.Male)) {
            proteinGoal = (float) ((weight * .73) * .8);
        }
        if (gender.equals(User.Gender.Female)) {
            proteinGoal = (float) ((weight * .62) * .8);
        }
    }
    
    public void intake (int calories, float fat, float carbs, float protein) {
        this.calories += calories;
        this.fat += fat;
        this.carbs += carbs;
        this.protein += protein;
    }
    
    public int getCalorieGoal() {
        return this.calorieGoal;
    }
    
    public float getProteinGoal() {
        return this.proteinGoal;
    }
    
    public int getCalories() {
        return this.calories;
    }
    
    public float getFat() {
        return this.fat;
    }
    
    public float getCarbs() {
        return carbs;
    }
    
    public float getProtein() {
        return protein;
    }       
}
