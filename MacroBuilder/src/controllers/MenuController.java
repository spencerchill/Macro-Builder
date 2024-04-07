/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import database.DatabaseUtil;
import database.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import objects.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author sweet
 */
public class MenuController implements Initializable {
 private DatabaseUtil databaseUtil;
    /**
     * Initializes the controller class.
     */
 @FXML
 private Label caloriesLabel;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            databaseUtil = new DatabaseUtil();
            User user = databaseUtil.getUserDetails();
            if (user != null) {
                float calories = user.getDay().getCalorieGoal(); 
                caloriesLabel.setText("Calories for Today: " + calories);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
         Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    
    
}
