/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package macrobuilder;

import java.sql.Date;
import objects.User;
import objects.Day;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KingJ
 */
public class DayTest {

    private Day day;

    public DayTest() {
    }

    @Before
    public void setUp() {
        User.Gender gender = User.Gender.MALE;
        int age = 20;
        float height = 180;
        float weight = 100;
        User.ActivityLevel activityLevel = User.ActivityLevel.ACTIVE;
        User.CurrentMode mode = User.CurrentMode.CUT;
        day = new Day(gender, age, height, weight, activityLevel, mode,0,0,0,0);
    }

    //Constructor should calculate calories and protein using info from constructor at time of creation.
    @Test
    public void testConstructor() {
        assertNotNull(day.getCalorieGoal());
        assertNotNull(day.getProtein());
    }

    @Test
    public void testGetters() {
        assertEquals(0, day.getCalories());
        assertEquals(0, day.getCarbs(), .001f);
        assertEquals(0, day.getFat(), .001f);
        assertEquals(0, day.getProtein(), .001f);
    }

    @Test
    public void testCalorieGoal() {
        //Using information from setUp()
        int expectedCalories = (int) (88.362 + (13.397 * 100) + (4.799 * 180) - (5.677 * 20));
        // CUT MODE
        expectedCalories *= .75;
        // ACTIVE
        expectedCalories += 600;
        assertEquals(expectedCalories, day.getCalorieGoal());
    }

    //Tests that gender effects calories
    @Test
    public void testCalorieGoalGender() {
        int expectedCaloriesMale = day.getCalorieGoal();

        //Changed Gender to Female
        day = new Day(User.Gender.FEMALE, 20, 180, 100, User.ActivityLevel.ACTIVE, User.CurrentMode.CUT,0,0,0,0);
        int expectedCaloriesFemale = day.getCalorieGoal();

        // Female should have less calories
        assertTrue(expectedCaloriesFemale < expectedCaloriesMale);
    }

    //Tests if activity level affects calories
    @Test
    public void testCalorieGoalActivity() {
        int expectedActiveCalories = day.getCalorieGoal();

        day = new Day(User.Gender.MALE, 20, 180, 100, User.ActivityLevel.MODERATELY_ACTIVE, User.CurrentMode.CUT,0,0,0,0);
        int expectedModerateCalories = day.getCalorieGoal();

        day = new Day(User.Gender.MALE, 20, 180, 100, User.ActivityLevel.NOT_ACTIVE, User.CurrentMode.CUT,0,0,0,0);
        int expectedNotActiveCalories = day.getCalorieGoal();

        //We add 300 if moderately active and 600 if active. Checking if true.
        assertEquals(expectedActiveCalories,  expectedNotActiveCalories + 600);
        assertEquals(expectedModerateCalories, expectedNotActiveCalories + 300);
    }

    //Test if mode affects calorie goal
    @Test
    public void testCalorieGoalMode() {
        //Set up starts with cut mode
        //Set activity level to Not Active so as to not interfere with calculations.
        day = new Day(User.Gender.MALE, 20, 180, 100, User.ActivityLevel.NOT_ACTIVE, User.CurrentMode.CUT,0,0,0,0);
        int expectedCutCalories = day.getCalorieGoal();

        day = new Day(User.Gender.MALE, 20, 180, 100, User.ActivityLevel.NOT_ACTIVE, User.CurrentMode.MAINTAIN,0,0,0,0);
        int expectedMaintainCalories = day.getCalorieGoal();

        day = new Day(User.Gender.MALE, 20, 180, 100, User.ActivityLevel.NOT_ACTIVE, User.CurrentMode.BULK,0,0,0,0);
        int expectedBulkCalories = day.getCalorieGoal();

        assertEquals(expectedCutCalories, (int) (expectedMaintainCalories * .75));
        assertEquals(expectedBulkCalories, (int) (expectedMaintainCalories * 1.25));
    }

    //Tests both Male and Female using given formulas.
    @Test
    public void testProteinGoal() {
        // MALE
        float expectedProteinMale = (float) (((100 * 2.205) * .73));
        assertEquals(expectedProteinMale, day.getProteinGoal(), 0.001f);
        // FEMALE
        day = new Day(User.Gender.FEMALE, 20, 180, 100, User.ActivityLevel.ACTIVE, User.CurrentMode.CUT,0,0,0,0);
        float expectedProteinFemale = (float) (((100 * 2.205) * .62));
        assertEquals(expectedProteinFemale, day.getProteinGoal(), 0.001f);
    }

    @Test
    public void testIntake() {
        Date date = Date.valueOf("2024-01-01");
        day.intake(250, 733.4f, 1002.3f, 20.999f, date);
        assertEquals(250, day.getCalories());
        assertEquals(733.4f, day.getFat(), .001f);
        assertEquals(1002.3f, day.getCarbs(), .001f);
        assertEquals(20.999f, day.getProtein(), .001f);
    }
}

