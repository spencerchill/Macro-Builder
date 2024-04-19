/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package macrobuilder;

import objects.Food;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests Food class using JUnit4
 *
 * @author KingJ
 */
public class FoodTest {

    public FoodTest() {
    }
    //also tests constructor

    @Test
    public void testGetters() {
        Food food = new Food("Watermelon", 1000, 5f, 20f,1f);
        assertEquals("Watermelon", food.getName());
        assertEquals(1000, food.getCalories());
        assertEquals(5f, food.getFat(), 0.01f);
        assertEquals(20f, food.getCarbs(), 0.01f);
        assertEquals(1f, food.getProtein(), 0.01f);
    }
}
