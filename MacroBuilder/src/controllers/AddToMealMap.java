/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import javafx.scene.control.Button;
import objects.Food;
import objects.Meal;

/**
 *  This class represents the relationship between food items and buttons in the 
 * meal creation menu
 * @author spencerhill
 */
public class AddToMealMap {
    Food food;
    Button addbtn;
    Button removeBtn;
    MenuController menu;
    
    /**
     * 4-parameter constructor that maps the food to the buttons and sets their actions
     * @param food
     * @param addBtn
     * @param removeBtn
     * @param menu 
     */
    public AddToMealMap(Food food, Button addBtn, Button removeBtn, MenuController menu) {
        this.food = food;
        this.addbtn = addBtn;
        this.removeBtn = removeBtn;
        this.menu = menu;
        
        addBtn.setOnAction(e -> menu.addToMeal(food));
        removeBtn.setOnAction(e -> menu.removeFromMeal(food));
    }
}
