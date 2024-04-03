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
import objects.User;

/**
 *
 * @author KingJ
 */
public class MenuController {
    private DatabaseUtil databaseUtil;
    private UserManager userManager;
    public MenuController() throws IOException {
        userManager = UserManager.getInstance();
        databaseUtil = new DatabaseUtil();
    }
    public User getUserDetails() throws SQLException {
        try(Connection connection = databaseUtil.getDatabaseConnection()) {
            String query = "SELECT * FROM user_details WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userManager.getUserId());
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    User.Gender gender = User.Gender.valueOf(resultSet.getString("gender"));
                    int age = resultSet.getInt("age");
                    float height = resultSet.getFloat("height");
                    float weight = resultSet.getFloat("weight");
                    User.ActivityLevel activityLevel = User.ActivityLevel.valueOf(resultSet.getString("activity_level"));
                    User.CurrentMode mode = User.CurrentMode.valueOf(resultSet.getString("current_mode"));
                    
                    return new User(userManager.getUsername(), gender, age, height, weight, activityLevel, mode);
                }
            }
        }
        return null;
    }
}
