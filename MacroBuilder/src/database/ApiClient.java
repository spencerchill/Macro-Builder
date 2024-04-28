/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Food;


/**
 * Class to manage reading and parsing JSON files. 
 * Uses the FDC api
 * @author KingJ
 */
public class ApiClient {
  private static URL url;
  private float fat;
  private int calories;
  private float carbs;
  private float protein;
  private String nutrientName;
  private static String apiUrl;
  private String foodText;
  private String apiKey;
    
    /**
     * Takes in users query and creates url from it
     * @param foodText 
     */
    public ApiClient(String foodText) {
      try {
          ConfigReader configReader = new ConfigReader("config.properties");
          apiKey = configReader.getApiKey();
          // handles spaces
          String encodedFoodText = URLEncoder.encode(foodText, "UTF-8");
          this.foodText = foodText;
          apiUrl = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=" + encodedFoodText;
      } catch (IOException ex) {
          Logger.getLogger(ApiClient.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    /**
     * Grabs name and selected macro information from food name
     * @return Food object.
     * @throws MalformedURLException 
     */
    public Food getFood() throws MalformedURLException{
                   // we have no list to display for autocomplete. so i just return the exact representation of the query.
                  JSONObject food = searchForFood(foodText);
                  // lmao just return food with null name then check if food has name = null
                  if(food== null){
                      return new Food("null", 0, 0.0f, 0.0f, 0.0f);
                  }
                  JSONArray nutrients = food.getJSONArray("foodNutrients");
                  //loop through nutrients array to find the ones we want (sucks).
                  for(int i = 0; i < nutrients.length(); i++){
                      
                      JSONObject nutrient = nutrients.getJSONObject(i);
                      nutrientName = nutrient.getString("nutrientName");
                       
                  switch (nutrientName) {
                      case "Energy" -> {
                          calories = nutrient.getInt("value");
                          }
                      case "Total lipid (fat)" -> {
                          fat = nutrient.getInt("value");
                          }
                      case "Carbohydrate, by difference" -> {
                          carbs = nutrient.getInt("value");

                          }
                      case "Protein" -> {
                          protein = nutrient.getInt("value");
                          }
                      default -> {
                          }
                  }
                  }
                  Food apiFood = new Food(foodText, calories, fat, carbs, protein);
                  return apiFood;
          }
    
    /**
     *  Sets up Http connection, uses string builder and loops to find food object that matches our query.
     * @param query string for food name
     * @return JSON object representing the searched food
     */
    public static JSONObject searchForFood(String query){
        try {
          ApiClient.url = new URL(apiUrl);
          
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          conn.setRequestMethod("GET");
          conn.connect();
          int responseCode = conn.getResponseCode();
          
          if(responseCode != 200){
              throw new RuntimeException("HttpResponseCode: " + responseCode);
          }
          else {
              StringBuilder informationString = new StringBuilder();
              Scanner scanner = new Scanner(url.openStream());
              while (scanner.hasNext()){
                  informationString.append(scanner.nextLine());
              }
              conn.disconnect();
             JSONObject json = new JSONObject(informationString.toString());
             JSONArray foodArray = json.getJSONArray("foods");
             for(int i = 0; i < foodArray.length(); i++){
                 JSONObject food = foodArray.getJSONObject(i);
                 if(food.getString("description").equalsIgnoreCase(query)){
                     return food;
                 }
             }
             return null;
                  }
          }catch (IOException | RuntimeException  ex) {
              Logger.getLogger(ApiClient.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
          
      }
    }