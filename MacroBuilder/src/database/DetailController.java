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
import objects.User.*;
/**
 * Controller for Detail scene.
 * @author KingJ
 */
public class DetailController {
      private DatabaseUtil databaseUtil;
      /**
       * Creates databaseUtil object and userManager to retrieve userID.
       * @throws IOException 
       */
      public DetailController() throws IOException {
          UserManager userManager = UserManager.getInstance();
          databaseUtil = new DatabaseUtil();
      }
      /**
       * Stores user details into database.
       * @param gender
       * @param age
       * @param height
       * @param weight
       * @param activityLevel
       * @param mode
       * @throws SQLException if database error.
       */
      public void storeUserDetails(Gender gender, int age, float height, float weight, ActivityLevel activityLevel, CurrentMode mode) throws SQLException {
          try(Connection connection = databaseUtil.getDatabaseConnection()) {
              UserManager userManager = UserManager.getInstance();
              String query = "INSERT INTO user_details (user_id, gender, age, height, weight, activity_level, current_mode) VALUES (?, ?, ?, ?, ?, ?, ?)";
              PreparedStatement statement = connection.prepareStatement(query);
              statement.setInt(1, userManager.getUserId());
              statement.setString(2, gender.name());
              statement.setInt(3, age);
              statement.setFloat(4, height);
              statement.setFloat(5, weight);
              statement.setString(6, activityLevel.name());
              statement.setString(7, mode.name());
              statement.executeUpdate();
          }
      }
}
