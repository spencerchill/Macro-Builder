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

/**
 *
 * @author KingJ
 */
public class ApiClient {
  private URL url;
  
// pass the text as parameter later
    public ApiClient() {
        // this is where ill concatenate url
    }
    
    public void getFood() throws MalformedURLException{
      try {
          this.url = new URL("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=APIKEY&query=Apple");
          
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
              JSONArray arr = json.getJSONArray("food");
              
              for(int i = 0; i < arr.length(); i++){
                  System.out.println("TBC");
              }
          }
          
      }catch (Exception  ex) {
              Logger.getLogger(ApiClient.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}
