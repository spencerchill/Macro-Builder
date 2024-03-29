/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author spencerhill
 * @author rimmycara
 * Represents a calendar for managing daily activity entry of a user
 */
public class Calendar {
    //Fields
    private User user; //Represents the user associated with the calendar
    private HashMap<String, Day> calendar; //Stores daily activity entries mappeed to their respective dates
    private Date now; // Represents the current date and time
    private String date; //Represents the current date in the format "MM//dd//yyyy"
    
    
    /**
     * Constructor to create a new Calendar object for the specified user
     * Initializes the calendar with the current date
     * @param user The user for whom the calendar is created
     */
    public Calendar(User user) {
        this.user = user;
        calendar = new HashMap<String, Day>();
        now = new Date();
        date = new SimpleDateFormat("MM/dd/yyyy").format(now);
        createDay(date); //Create a day entry for the current date
    }
    /**
     * Creates a new daily activity entry for the specified date if it does not already exist.
     * @param date The date for which the daily activity entry is to be created
     */
    public void createDay(String date) {
        if (!calendar.containsKey(date)) {
            //Create a new day entry with user attributes
        Day day = new Day(user.getGender(), user.getAge(), user.getHeight(), 
                user.getWeight(), user.getActivityLevel(), user.getCurrentMode());
        calendar.put(date, day); // Add the day entry to the calendar
        }
    }
    
    /**
     * Updates the current date and time stored in the 'now' and 'date' fields
     */
    public void updateDate() {
        now = new Date();
        date = new SimpleDateFormat("MM/dd/yyyy").format(now);
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
    public Day getDay(String date) {
        if(!calendar.containsKey(date)) {
            createDay(date); // Create a day entry if it doesn't exist
        }
        return calendar.get(date); // Retrieve and return day entry
    }
}
