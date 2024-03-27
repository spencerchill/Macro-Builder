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
 */
public class Calendar {
    private User user;
    private HashMap<String, Day> calendar;
    private Date now;
    private String date;
    
    public Calendar(User user) {
        this.user = user;
        calendar = new HashMap<String, Day>();
        now = new Date();
        date = new SimpleDateFormat("MM/dd/yyyy").format(now);
        createDay(date);
    }
    
    public void createDay(String date) {
        if (!calendar.containsKey(date)) {
        Day day = new Day(user.getGender(), user.getAge(), user.getHeight(), 
                user.getWeight(), user.getActivityLevel(), user.getCurrentMode());
        calendar.put(date, day);
        }
    }
    
    public void updateDate() {
        now = new Date();
        date = new SimpleDateFormat("MM/dd/yyyy").format(now);
    }
    
    public String getDate() {
        updateDate();
        return date;
    }
    
    public Day getDay(String date) {
        if(!calendar.containsKey(date)) {
            createDay(date);
        }
        return calendar.get(date);
    }
}
