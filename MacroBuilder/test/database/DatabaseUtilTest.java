/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package database;

import java.io.IOException;
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
 * @author KingJ
 */
public class DatabaseUtilTest {

    private static String url;
    private static String username;
    private static String password;

    public DatabaseUtilTest() throws IOException {
        ConfigReader configReader = new ConfigReader("config.properties");
        url = configReader.getUrl();
        username = configReader.getUsername();
        password = configReader.getPassword();
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of getDatabaseConnection method, of class databaseConnection.
     */
    @Test
    public void testGetDatabaseConnection() throws SQLException {
        try ( Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM users";
            try ( Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("username") + " " + resultSet.getString("Password"));
                }
            }
        } catch (SQLException e) {
            System.out.println("connection  failed" + e.getMessage());
            e.printStackTrace();
        }
    }

}
