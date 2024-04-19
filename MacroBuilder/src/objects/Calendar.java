/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import database.DatabaseUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.sql.Date;
/**
 *
 * @author spencerhill
 * @author rimmycara
 * Represents a calendar for managing daily activity entry of a user
 */
public class Calendar {
    //Fields
    private User user; //Represents the user associated with the calendar
    private HashMap<java.sql.Date, Day> calendar; //Stores daily activity entries mappeed to their respective dates
    private Date now; // Represents the current date and time
    private String date; //Represents the current date in the format "MM//dd//yyyy"
    private DatabaseUtil databaseUtil;
    java.sql.Date sqlDate;
    private DateFormat formatter;
    /**
     * Constructor to create a new Calendar object for the specified user
     * Initializes the calendar with the current date
     * @param user The user for whom the calendar is created
     * @throws java.sql.SQLException
     */
    public Calendar(User user) throws SQLException {
        this.user = user;
        calendar = new HashMap<java.sql.Date, Day>();
        now = new Date();
         formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
        String dateString = formatter.format(now);
        sqlDate = java.sql.Date.valueOf(dateString);
       
        databaseUtil = new DatabaseUtil();
        // load days into hashmap so we know if we already have a day.
        }catch (IllegalArgumentException e) {
            System.out.println("Error converting date: " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.calendar = databaseUtil.loadDays(calendar, this.user);
        System.out.println("I LOADED");
        createDay(sqlDate); //Create a day entry for the current date
        }
    /**
     * Creates a new daily activity entry for the specified date if it does not already exist.
     * @param sqlDate
     */
    public  void createDay(java.sql.Date sqlDate) {
        if (!calendar.containsKey(sqlDate)) {
            //Create a new day entry with user attributes
            Day day = new Day(user.getGender(), user.getAge(), user.getHeight(), user.getWeight(), user.getActivityLevel(), user.getCurrentMode());
           
            try {
                databaseUtil.addDay(user, sqlDate);
            } catch (SQLException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        calendar.put(sqlDate, day); // Add the day entry to the calendar
        }
    }
    
    /**
     * Updates the current date and time stored in the 'now' and 'date' fields
     */
    public void updateDate() {
        now = new Date();
        String dateString = formatter.format(now);
        this.sqlDate =  java.sql.Date.valueOf(dateString);
    }
    
    /**
     * Retrieves the current date in "MM/dd/yyyy" format.
     * @return The current date.
     */
    public String getDate() {
        updateDate(); //Update the current date
        return date; //Return the current date
    }
    
    /**
     * Retrieves the daily activity entry for the specified date.
     * If the entry does not exist, creates a new one.
     * @param date The date for which the daily activity entry is to be retrieved.
     * @return The Day object representing the daily activity entry.
     */
    public Day getDay(java.sql.Date date) {
        if(!calendar.containsKey(date)) {
            createDay(date); // Create a day entry if it doesn't exist
        }
        return calendar.get(date); // Retrieve and return day entry
    }
}
