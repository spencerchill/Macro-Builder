/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import objects.Meal;

/**
 *  This class represents that relationship between meal items 
 * and their buttons in the meals and foods menu
 * @author spencerhill
 */
public class MealMap {
    Meal meal;
    Button eatbtn;
    Button deleteButton;
    VBox mealVBox;
    MenuController menu;
    
    /**
     * 5-parameter constructor that maps the meal to its buttons and sets their actions
     * @param meal
     * @param eatBtn
     * @param deleteBtn
     * @param mealVBox
     * @param menu 
     */
    public MealMap(Meal meal, Button eatBtn, Button deleteBtn, VBox mealVBox, MenuController menu) {
        this.meal = meal;
        this.eatbtn = eatBtn;
        this.deleteButton = deleteBtn;
        this.mealVBox = mealVBox;
        this.menu = menu;
        
        eatBtn.setOnAction(e -> menu.eatMeal(meal));
        
        deleteBtn.setOnAction(e -> {
            try {
                menu.deleteMeal(meal, mealVBox);
            } catch (SQLException ex) {
                Logger.getLogger(MealMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
