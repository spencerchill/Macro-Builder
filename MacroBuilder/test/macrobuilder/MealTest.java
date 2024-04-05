/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package macrobuilder;

import java.util.ArrayList;
import objects.Food;
import objects.Meal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Testing for meal class using JUnit 4
 * @author KingJ
 */
public class MealTest {
    private Meal meal;
    private Food food1;
    private Food food2;
    public MealTest() {
    }
    
    @Before
    public void setUp() {
       meal = new Meal();
       food1 = new Food("Watermelon", 1000, 50f, 10f, 20f, 13.2f);
       food2 = new Food("Cheese", 60, 30.2f, 900f, 10f, 10.8f);
    }
    
    @Test
    public void testAddFood() {
        meal.addFood(food1);
        meal.addFood(food2);
        
        assertEquals(2, meal.getFoods().size());
        assertEquals(1060, meal.getTotalCalories());
        assertEquals(80.2, meal.getTotalFat(), 0.01f);
        assertEquals(910, meal.getTotalCarbs(), 0.01f);
        assertEquals(30, meal.getTotalSugar(), 0.01f);
        assertEquals(24, meal.getTotalProtein(), 0.01f);
    }
    
    @Test
    public void testRemoveFood(){
        meal.addFood(food1);
        meal.addFood(food2);
        meal.removeFood(food2);
        assertEquals(1, meal.getFoods().size());
        assertEquals(1000, meal.getTotalCalories());
        assertEquals(50, meal.getTotalFat(), 0.01f);
        assertEquals(10, meal.getTotalCarbs(), 0.01f);
        assertEquals(20, meal.getTotalSugar(), 0.01f);
        assertEquals(13.2, meal.getTotalProtein(), 0.01f);
    }

}
