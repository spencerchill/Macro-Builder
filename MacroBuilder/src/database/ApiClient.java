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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Food;

/**
 *
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
// pass the text as parameter later
    public ApiClient(String foodText) {
      try {
          ConfigReader configReader = new ConfigReader("config.properties");
          apiKey = configReader.getApiKey();
          this.foodText = foodText;
          apiUrl = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=" + foodText;
      } catch (IOException ex) {
          Logger.getLogger(ApiClient.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    public Food getFood() throws MalformedURLException{
                   // we have no list to display for autocomplete. so i just return the exact representation of the query.
                  JSONObject food = searchForFood(foodText);
                  // lmao just return food with null name then check if food has name = null
                  if(food== null){
                      return new Food("null", 0, 0.0f, 0.0f, 0.0f);
                  }
                  // Name of food
                  String description = food.getString("description");
                  System.out.println(description);
                  
                  JSONArray nutrients = food.getJSONArray("foodNutrients");
                  //loop through nutrients array to find the ones we want (sucks).
                  for(int i = 0; i < nutrients.length(); i++){
                      
                      JSONObject nutrient = nutrients.getJSONObject(i);
                      nutrientName = nutrient.getString("nutrientName");
                       
                  switch (nutrientName) {
                      case "Energy" -> {
                          calories = nutrient.getInt("value");
                          System.out.println("Calories: " + calories);
                          }
                      case "Total fat (NLEA)" -> {
                          fat = nutrient.getInt("value");
                          System.out.println("Fat: " + fat);
                          }
                      case "Carbohydrate, by difference" -> {
                          carbs = nutrient.getInt("value");
                          System.out.println("Carbs: " + carbs);
                          }
                      case "Protein" -> {
                          protein = nutrient.getInt("value");
                          System.out.println("Protein: " + protein);
                          }
                      default -> {
                          }
                  }
                  }
                  Food apiFood = new Food(foodText, calories, fat, carbs, protein);
                  return apiFood;
          }
    
    // searches for the exact match to the query.
    // had to do this because i dont have  a list to be able to show
    // so if user enters yello i retriev corn im like brother.
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
