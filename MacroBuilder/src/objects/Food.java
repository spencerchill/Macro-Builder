/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

/**
 *
 * @author King
 */
public class Food {
    private String name;
    private int calories;
    private float fat;
    private float carbs;
    private float sugar;
    private float protein;
    
    public Food(String name, int calories, float fat, float carbs, float sugar, float protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.sugar = sugar;
        this.protein = protein;
    }
    
    public String getName(){
        return name;
    }
    
    public int getCalories(){
        return calories;
    }
    
    public float getFat(){
        return fat;
    }
    
    public float getCarbs(){
        return carbs;
    }
    
    public float getProtein(){
        return protein;
    }
    
    public float getSugar() {
        return sugar;
    }

}
