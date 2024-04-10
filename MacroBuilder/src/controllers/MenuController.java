/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import database.DatabaseUtil;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

/**
 * FXML Controller class for Menu
 *
 * @author KingJ
 */
public class MenuController implements Initializable {

    private DatabaseUtil databaseUtil;

    private User user;

    @FXML
    private Label userLabel;

    @FXML
    private Label caloriesLabel;

    @FXML
    private Button submitButton;

    @FXML
    private TextField caloriesField;

    @FXML
    private PieChart caloriesPieChart;
    
    @FXML
    private ProgressBar progressBar;
    
    private double progress;
    
    @FXML 
    public Label curModeLabel;
    
    @FXML
    public Label fatLabel;
    
    @FXML
    public Label carbLabel;
    
    @FXML
    public Label proteinLabel;
    /**
     * Initializes controller class. Retrieves user from database and updates
     * labels on screen.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            databaseUtil = new DatabaseUtil();
            user = databaseUtil.getUserDetails();
            if (user != null) {
                updateCalories();
                updateUser();
                updateMode();
                updateMacroLabels();
            }
            updatePieChart();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updates pie chart with current remaining and consumed calories.
     */
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
    /**
     * Updates progress bar with current remaining and consumed calories.
     */
    public void updateProgressBar(){
        double calorieGoal = user.getDay().getCalorieGoal();
        double consumedCalories = user.getDay().getCalories();
        progress = consumedCalories / calorieGoal;
        progressBar.setProgress(progress);
    }

    /**
     * Handles quick add calories button. Updates displays.
     *
     * @param event Triggered by submit button.
     */
    @FXML
    void submitCalories(ActionEvent event) {
        String caloriesText = caloriesField.getText();
        if (!caloriesText.isEmpty()) {
            int calories = Integer.parseInt(caloriesText);
            user.getDay().intake(calories, 0, 0, 0);
            updatePieChart();
            updateProgressBar();
            caloriesField.clear();
        }
    }

    /**
     * Updates users calories. Used in initialization to show goal.
     */
    private void updateCalories() {
        caloriesLabel.setText("Calorie Goal: " + (int) user.getDay().getCalorieGoal());
    }
    
    private void updateMode() {
        curModeLabel.setText(user.getModeAsString());
    }

    private void updateMacroLabels() {
        fatLabel.setText("Fat - " + Integer.toString( (int) user.getDay().getFatGoal()));
        carbLabel.setText("Carbs - " + Integer.toString( (int) user.getDay().getCarbGoal()));
        proteinLabel.setText("Protein - " + Integer.toString( (int) user.getDay().getProteinGoal()));
    }
    /**
     * Updates greeting label after we retrieve user from database.
     */
    private void updateUser() {
        userLabel.setText("Hey, " + user.getUsername() + "!");
    }
}
