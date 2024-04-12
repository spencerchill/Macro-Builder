/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

/**
 * Represents a food item with nutritional information.
 * This class provides methods to retrieve the name, calories, fat, carbs, sugar, and protein
 * @author rimmycara
 * @author King
 */
public class Food {
    //Fields
    private String name; //Name of the food item
    private int calories; //Calories per serving
    private float fat; //Fat content per serving
    private float carbs; //Carbs content per serving
    private float protein; //Protein content per serving
    
    
    /**
     * Constructor to create a new Food object with specified nutritional information
     * @param name The name of the food item
     * @param calories The calorie content per serving
     * @param fat  The fat content per serving
     * @param carbs The carbs content per serving
     * @param sugar The sugar content per serving
     * @param protein The protein content per serving
     */
    public Food(String name, int calories, float fat, float carbs, float protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }
    /**
     * Retrieves the name of the food item
     * @return name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Retrieves the calorie content per serving
     * @return calories
     */
    public int getCalories(){
        return calories;
    }
    /**
     * Retrieves the fat content per serving
     * @return fat
     */
    public float getFat(){
        return fat;
    }
    /**
     * Retrieves the carbs content per serving
     * @return carbs
     */
    public float getCarbs(){
        return carbs;
    }
    /**
     * Retrieves the protein content per serving
     * @return protein
     */
    public float getProtein(){
        return protein;
    }

}
