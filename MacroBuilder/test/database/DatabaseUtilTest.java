/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sweet
 */
public class DatabaseUtilTest {
    private static final String URL = " ";
    private static final String USERNAME = " ";
    private static final String PASSWORD = " ";
    
    public DatabaseUtilTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getDatabaseConnection method, of class databaseConnection.
     */
    @Test
    public void testGetDatabaseConnection() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM users";
           try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
               while(resultSet.next()) {
                   //Should print out jayden isCool
                   System.out.println(resultSet.getString("username") + " " + resultSet.getString("Password"));
               }
           }
        }
        catch (SQLException e) {
            System.out.println( "connection  failed" + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
