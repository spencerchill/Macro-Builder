/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author spencerhill
 */
public class FoodCatalog {
    private ArrayList<Food> catalog;
    
    public FoodCatalog () {
        catalog = new ArrayList<Food>();
    }
    
    public FoodCatalog(ArrayList<Food> list) {
        catalog = list;
    }
    
    public void addFood(Food food) {
        catalog.add(food);
    }
    
    public Food getFood(int position) {
        return catalog.get(position);
    }
    
    public int size() {
        return catalog.size();
    }
}
