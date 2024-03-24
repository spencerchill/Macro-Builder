/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author KingJ
 */
public class databaseConnection {
    private static final String URL = " ";
    private static final String USERNAME = " ";
    private static final String PASSWORD = " ";
            
    
    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
                }
}
