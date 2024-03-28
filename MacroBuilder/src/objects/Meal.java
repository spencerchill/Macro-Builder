/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.util.ArrayList;

/**
 *
 * @author KingJ
 */
public class Meal {

    private ArrayList<Food> foods;
    private int totalCalories;
    private float totalFat;
    private float totalCarbs;
    private float totalProtein;
    private float totalSugar;

    public Meal() {
        this.foods = new ArrayList<>();
        this.totalCalories = 0;
        this.totalFat = 0.0f;
        this.totalCarbs = 0.0f;
        this.totalProtein = 0.0f;
        this.totalSugar = 0.0f;
    }

    public void addFood(Food food) {
        foods.add(food);
        totalCalories += food.getCalories();
        totalFat += food.getFat();
        totalCarbs += food.getCarbs();
        totalProtein += food.getProtein();
        totalSugar += food.getSugar();
    }

    public void removeFood(Food food) {
        foods.remove(food);
        totalCalories -= food.getCalories();
        totalFat -= food.getFat();
        totalCarbs -= food.getCarbs();
        totalProtein -= food.getProtein();
        totalSugar -= food.getSugar();
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public float getTotalFat() {
        return totalFat;
    }

    public float getTotalCarbs() {
        return totalCarbs;
    }

    public float getTotalProtein() {
        return totalProtein;
    }

    public float getTotalSugar() {
        return totalSugar;
    }

}
