/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kingj
 */
public class ChartData {
    
    private ArrayList<String> dates;
    private ArrayList<Float> weights;
    private User user;
    private Day day;
    
    public ChartData(User user){
        dates = new ArrayList();
        weights = new ArrayList();
        this.user = user;
    }
    
    public void populateChartArray(){
        LocalDate currentDate = LocalDate.now();
        // DateTimeFormatter for formatting dates
        DateTimeFormatter mysqlFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // DateTimeFormatter for displaying dates in the chart
        DateTimeFormatter chartFormatter = DateTimeFormatter.ofPattern("M/d");
        
        for(int i = 6; i >= 0; i--){
            LocalDate date = currentDate.minusDays(i);
            String chartDate = date.format(chartFormatter);
            String mysqlDate = date.format(mysqlFormatter);
            java.sql.Date sqlDate = java.sql.Date.valueOf(mysqlDate);
            
            float weight = 0.0f; 
            Day day = user.getCalendar().getDay(sqlDate);
             if (day != null) {
                 weight = day.getWeight();
                 weights.add(weight);
             }
             else {
                 weights.add(0.0f);
             }
             dates.add(chartDate);
        }
        
        System.out.println("Dates: " + dates);
        System.out.println("Weights: " + weights);  
    }
    
    public ArrayList<String> getDates(){
        return this.dates;
    }
    
    public ArrayList<Float> getWeights(){
        return this.weights;
    }
}
