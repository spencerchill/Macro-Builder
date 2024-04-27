/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Allows config file to be read
 * @author KingJ
 */
public class ConfigReader {

    private Properties prop;
    
    /**
     * Takes in file path and loads file
     * @param path
     * @throws IOException 
     */
    public ConfigReader(String path) throws IOException {
        prop = new Properties();
        prop.load(new FileInputStream(path));
    }
    /**
     * returns url credential for database
     * @return 
     */
    public String getUrl() {
        return prop.getProperty("db.url");
    }
    /**
     * returns username credential for database
     * @return 
     */
    public String getUsername() {
        return prop.getProperty("db.username");
    }
    /**
     * returns password credential for database
     * @return 
     */
    public String getPassword() {
        return prop.getProperty("db.password");
    }
    /**
     * returns api key.
     * @return 
     */
      public String getApiKey(){
        return prop.getProperty("api.key");
    }
}
