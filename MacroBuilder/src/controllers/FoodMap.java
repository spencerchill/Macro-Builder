package controllers;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import objects.Food;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *  This class represents the relationship between 
 * a food item and their buttons
 * @author spencerhill
 */
public class FoodMap {
    Food food;
    Button eatbtn;
    Button deleteButton;
    VBox foodVBox;
    MenuController menu;
    
    /**
     * 5-parameter constructor that sets the actions of the buttons
     * 
     * @param food
     * @param eatBtn
     * @param deleteBtn
     * @param foodBox
     * @param menu 
     */
    public FoodMap(Food food, Button eatBtn, Button deleteBtn, VBox foodBox, MenuController menu) {
        this.food = food;
        this.eatbtn = eatBtn;
        this.deleteButton = deleteBtn;
        this.foodVBox = foodBox;
        this.menu = menu;
        
        eatBtn.setOnAction(e -> menu.eatFood(food));
        
        deleteBtn.setOnAction(e -> {
            try {
                menu.deleteFood(food, this.foodVBox);
            } catch (SQLException ex) {
                Logger.getLogger(FoodMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
}
