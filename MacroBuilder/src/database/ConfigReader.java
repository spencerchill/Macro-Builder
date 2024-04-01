/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author KingJ
 */
public class ConfigReader {

    private Properties prop;

    public ConfigReader(String path) throws IOException {
        prop = new Properties();
        prop.load(new FileInputStream(path));
    }

    public String getUrl() {
        return prop.getProperty("db.url");
    }

    public String getUsername() {
        return prop.getProperty("db.username");
    }

    public String getPassword() {
        return prop.getProperty("db.password");
    }
}
