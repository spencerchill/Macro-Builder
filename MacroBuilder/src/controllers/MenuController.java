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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private Label userLabel;
    
    private User user;
    @FXML
    private Label caloriesLabel;
  
    @FXML
    private Button submitButton;

    @FXML
    private TextField caloriesField;
    
    @FXML
private PieChart caloriesPieChart;
    

private void updatePieChart() {
           caloriesPieChart.setLegendVisible(false);
            caloriesPieChart.setLabelLineLength(15);
            int remainingCalories = user.getDay().getRemainingCalories();
            int consumedCalories = user.getDay().getCalories();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
   
            new PieChart.Data(remainingCalories + " Calories Remaining", remainingCalories),
            new PieChart.Data(consumedCalories + " Calories Consumed", consumedCalories)
    );
    caloriesPieChart.setData(pieChartData);
}

    
    @FXML
    void submitCalories(ActionEvent event) {
        String caloriesText = caloriesField.getText();
        if (!caloriesText.isEmpty()) {
            int calories = Integer.parseInt(caloriesText);
            user.getDay().intake(calories, 0, 0, 0);
            updateCalories();
            updatePieChart();
            caloriesField.clear();
        }
    }
    
    private void updateCalories() {
        caloriesLabel.setText("Calorie Goal: " + (int) user.getDay().getCalorieGoal());
    }
    
    private void updateUser(){
        userLabel.setText("Hey, " + user.getUsername() + "!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            databaseUtil = new DatabaseUtil();
            user = databaseUtil.getUserDetails();
            if (user != null) {
                updateCalories();
                updateUser();
            }
            updatePieChart(); 
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
