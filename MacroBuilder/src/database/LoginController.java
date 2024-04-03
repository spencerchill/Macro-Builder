/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author KingJ
 */
public class LoginController {

    private DatabaseUtil databaseUtil;

    public LoginController() throws IOException {
        databaseUtil = new DatabaseUtil();
    }

    public boolean loginUser(String username, String password) throws SQLException {
        try ( Connection connection = databaseUtil.getDatabaseConnection()) {
            String query = "SELECT password, salt FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    String salt = resultSet.getString("salt");
                    String inputPassword = databaseUtil.hashPassword(password, salt);
                    return storedPassword.equals(inputPassword);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean firstLogin(String username) throws SQLException {
        try ( Connection connection = databaseUtil.getDatabaseConnection()) {
            String query = "SELECT * FROM user_details WHERE user_id = (SELECT id FROM users WHERE username = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            try ( ResultSet resultSet = statement.executeQuery()) {
                //returns true 
                return !resultSet.next();
            }
        }
    }

    public int getUserId(String username) throws SQLException {
        try ( Connection connection = databaseUtil.getDatabaseConnection()) {
            String query = "SELECT id FROM users WHERE username = ?";
            try ( PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try ( ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        }
        return -1;
    }
}
