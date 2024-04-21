/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package macrobuilder;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
         user.initializeCalendar();
         this.calender = user.getCalendar();
        } catch (SQLException ex) {
            Logger.getLogger(CalendarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDate(){
        now = new Date();
         DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         String dateString = formatter.format(now);
         java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
        //Calender should have day created that matches current date.
        assertNotNull(calender.getDay(sqlDate));
    }
    
    @Test
    public void testDayCreation(){
        java.sql.Date sqlDate = java.sql.Date.valueOf("2005-05-05");
        calender.createDay(sqlDate);
        assertNotNull(calender.getDay(sqlDate));
    }
    
    }
