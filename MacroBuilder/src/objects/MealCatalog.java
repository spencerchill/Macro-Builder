/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.util.ArrayList;

/**
 *  This class represents a catalog of meals that a user has created
 * @author spencerhill
 */
public class MealCatalog {
    private ArrayList<Meal> catalog;
    
    /**
     * 0-parameter constructor that initializes an empty array list of meals
     */
    public MealCatalog () {
        catalog = new ArrayList<Meal>();
    }
    
    /**
     * 1-parameter constructor that initialized the meal catalog to a given 
     * array list of meals
     * @param list 
     */
    public MealCatalog(ArrayList<Meal> list) {
        catalog = list;
    }
    
    /**
     * method that adds a given meal to the meal catalog
     * @param meal 
     */
    public void addMeal(Meal meal) {
        catalog.add(meal);
    }
    
    /**
     * method that returns a meal item from the meal catalog given a position
     * @param position
     * @return 
     */
    public Meal getMeal(int position) {
        return catalog.get(position);
    }
    
    /**
     * returns the number of meals in a users catalog
     * @return 
     */
    public int size() {
        return catalog.size();
    }
    
    /**
     * method that removes a given meal item from a users
     * meal catalog
     * @param meal 
     */
    public void removeMeal(Meal meal) {
        catalog.remove(meal);
    }
}
