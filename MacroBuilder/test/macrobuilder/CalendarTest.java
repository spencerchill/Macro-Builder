/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package macrobuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import objects.Calendar;
import objects.Day;
import objects.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Tests Calendar class using JUnit 4
 * @author KingK
 */
public class CalendarTest {
    private User user;
    private Calendar calender;
    private Date now;
    private String date; 

    public CalendarTest() {
    }
    
    @Before
    public void setUp() {
        user = new User("JaydenTheKing", User.Gender.FEMALE, 20, 68f, 200f, User.ActivityLevel.ACTIVE, User.CurrentMode.BULK);
        calender = user.getCalendar();
    }
    
    @Test
    public void testDate(){
        now = new Date();
        date = new SimpleDateFormat("MM/dd/yyyy").format(now);
        //Calender should have day created that matches current date.
        assertNotNull(calender.getDay(date));
    }
    
    @Test
    public void testDayCreation(){
        calender.createDay("05/05/2005");
        assertNotNull(calender.getDay("05/05/2005"));
    }
    
    }
