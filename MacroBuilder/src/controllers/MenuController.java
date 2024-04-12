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
    private ProgressBar calProgressBar;
    
    @FXML
    private ProgressBar fatProgressBar;
    
    @FXML
    private ProgressBar carbsProgressBar;
    
    @FXML
    private ProgressBar proteinProgressBar;
    
    private double calProgress;
    private double fatProgress;
    private double carbsProgress;
    private double proteinProgress;
    
    @FXML 
    public Label curModeLabel;
    
    @FXML
    public Label fatLabel;
    
    @FXML
    public Label carbLabel;
    
    @FXML
    public Label proteinLabel;
    
    @FXML
    public Label calLabel;
    
    @FXML
    public Label calLabelStart;
    
    @FXML
    public Label proteinLabelStart;
    
    @FXML
    public Label fatLabelStart;
    
    @FXML
    public Label carbLabelStart;
    
    @FXML
    public Label progressBarLabel;
    
    
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
                updateMainLabels();
                updateUser();
                updateMode();
                updateMacroLabels();
                setInitialLabels();
                
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
    public void updateCalProgressBar(){
        double calorieGoal = user.getDay().getCalorieGoal();
        double consumedCalories = user.getDay().getCalories();
        calProgress = consumedCalories / calorieGoal;
        calProgressBar.setProgress(calProgress);
    }
    /**
     * Updates the Fat Progress bar with current remaining and consumed fat
     */
    
    @FXML
    public void updateFatProgressBar() {
        double fatGoal = user.getDay().getFatGoal();
        double consumedFat = user.getDay().getFat();
        fatProgress = consumedFat / fatGoal;
        fatProgressBar.setProgress(fatProgress);
        
    }
    
    /**
     * Updates the Carbs Progress bar with current remaining and consumed Carbs
     */
    @FXML
    public void updateCarbsProgressBar() {
        double carbsGoal = user.getDay().getCarbGoal();
        double consumedCarbs = user.getDay().getCarbs();
        carbsProgress = consumedCarbs / carbsGoal;
        carbsProgressBar.setProgress(carbsProgress);
    }
    /**
     * Updates the ProteinProgressBar with current remaining and consumed Protein
     */
    @FXML
    public void updateProteinProgressBar() {
        double proteinGoal = user.getDay().getProteinGoal();
        double consumedProtein = user.getDay().getProtein();
        proteinProgress = consumedProtein / proteinGoal;
        proteinProgressBar.setProgress(fatProgress); 
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
            updateCalProgressBar();
            caloriesField.clear();
        }
    }
    

    /**
     * Updates users calories. Used in initialization to show goal.
     */
    private void updateMainLabels() {
        caloriesLabel.setText("Calorie Goal: " + (int) user.getDay().getCalorieGoal());
        progressBarLabel.setText("Progress: ");
    }
    
    private void updateMode() {
        curModeLabel.setText(user.getModeAsString());
    }
    
    private void setInitialLabels() {
        fatLabelStart.setText("0");
        carbLabelStart.setText("0");
        proteinLabelStart.setText("0");
        calLabelStart.setText("0");
    }

    private void updateMacroLabels() {
        fatLabel.setText("Fat - " + Integer.toString( (int) user.getDay().getFatGoal()));
        carbLabel.setText("Carbs - " + Integer.toString( (int) user.getDay().getCarbGoal()));
        proteinLabel.setText("Protein - " + Integer.toString( (int) user.getDay().getProteinGoal()));
        calLabel.setText("Calories - " + Integer.toString((int) user.getDay().getCalorieGoal()));
    }
    /**
     * Updates greeting label after we retrieve user from database.
     */
    private void updateUser() {
        userLabel.setText("Hey, " + user.getUsername() + "!");
    }
}
