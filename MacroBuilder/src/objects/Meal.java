/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.util.ArrayList;

/**
 * Represents a meal consisting of multiple food items
 * This class provides methods to add and remove food items from the meal,
 * as well as retrieve total nutritional information for the meal.
 * @author KingJ
 * @author rimmycara
 */
public class Meal {
    //Fields
    private ArrayList<Food> foods; // List of food items in the meal
    private String name;        //name of Meal
    private int totalCalories; //Total calories in the meal
    private float totalFat; //Total fat content in the meal
    private float totalCarbs; //Total carbs content in the meal
    private float totalProtein; //Total protein content in the meal

    /**
     * Construct a new Meal object with default values.
     * Initializes an empty list of foods and sets all nutritional totals to zero.
     */
    public Meal() {
        this.foods = new ArrayList<>();
        this.name = "";
        this.totalCalories = 0;
        this.totalFat = 0.0f;
        this.totalCarbs = 0.0f;
        this.totalProtein = 0.0f;
    }
    
    /**
     * method that sets the name of a meal
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method that returns the name of the meal as a string
     * @return 
     */
    public String getName() {
        return this.name;
    }
    /**
     * Adds a food item to the meal and updates the total nutritional information
     * @param food The food item to be added to the meal
     */
    public void addFood(Food food) {
        foods.add(food);
        calc();
    }
    /**
     * Removes a food item from the meal and updates the total nutritional information.
     * @param food The food item to be removed from the meal.
     */
    public void removeFood(Food food) {
        foods.remove(food); //Removes the food item from the list.
        calc();
    }
    /**
     * Retrieves the list of food items in the meal
     * @return foods
     */
    public ArrayList<Food> getFoods() {
        return foods;
    }
    
    /**
     * calculates the calorie and macros in a meal
     */
    public void calc() {
        totalCalories = 0;
        totalFat = 0;
        totalCarbs = 0;
        totalProtein = 0;
        
        for (int i = 0; i < foods.size(); i++) {
            totalCalories += foods.get(i).getCalories();
            totalFat += foods.get(i).getFat();
            totalCarbs += foods.get(i).getCarbs();
            totalProtein += foods.get(i).getProtein();
        }
    }
    /**
     * Retrieves the total calorie content of the meal
     * @return totalCalories
     */
    public int getTotalCalories() {
        return totalCalories;
    }
    /**
     * Retrieves the total fat content of the meal.
     * @return The totalFat
     */
    public float getTotalFat() {
        return totalFat;
    }
    /**
     * Retrieves the total carbs content of the meal
     * @return totalCarbs
     */
    public float getTotalCarbs() {
        return totalCarbs;
    }
    /**
     * Retrieves the total protein content of the meal
     * @return totalProtein
     */
    public float getTotalProtein() {
        return totalProtein;
    }
}
