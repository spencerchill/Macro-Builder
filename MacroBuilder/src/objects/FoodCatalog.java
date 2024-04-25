/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  This class represents the list of foods that a user
 * has currently created
 * @author spencerhill
 */
public class FoodCatalog {
    private ArrayList<Food> catalog;
    
    /**
     * 0-parameter constructor that initializes an array list of foods
     */
    public FoodCatalog () {
        catalog = new ArrayList<Food>();
    }
    
    /**
     * 1-parameter constructor that initializes an array list of foods
     * to a given array list
     * @param list 
     */
    public FoodCatalog(ArrayList<Food> list) {
        catalog = list;
    }
    
    /**
     * Method that adds a given food to the catalog
     * @param food 
     */
    public void addFood(Food food) {
        catalog.add(food);
    }
    
    /**
     * method that returns a food item from the catalog
     * given a position
     * @param position
     * @return 
     */
    public Food getFood(int position) {
        return catalog.get(position);
    }
    
    /**
     * method that returns the number of items in the catalog
     * @return 
     */
    public int size() {
        return catalog.size();
    }
    
    /**
     * method that removes a given food item from the catalog
     * @param food 
     */
    public void removeFood(Food food) {
        catalog.remove(food);
    }
}
