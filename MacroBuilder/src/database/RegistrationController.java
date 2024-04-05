/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 * Controller for registration scene.
 * @author KingJ
 */
public class RegistrationController {
    private DatabaseUtil databaseUtil;
    
    /**
     * Creates databaseUtil object
     * @throws IOException 
     */
    public RegistrationController() throws IOException{
        databaseUtil = new DatabaseUtil();
    }
    
    /**
     *  Inserts username and hashed password into database.
     * @param username
     * @param password
     * @throws SQLException 
     */
    public void registerUser(String username, String password) throws SQLException{
        String salt = databaseUtil.generateSalt();
        String hashedPassword = databaseUtil.hashPassword(password, salt);
        
        try (Connection connection = databaseUtil.getDatabaseConnection()){
            String query = "INSERT INTO users (username, password, salt) VALUES (?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, hashedPassword);
                statement.setString(3, salt);
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
