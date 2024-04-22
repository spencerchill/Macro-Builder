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
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import objects.ChartData;
import objects.Day;
import objects.Food;
import objects.FoodCatalog;

/**
 * FXML Controller class for Menu
 *
 * @author KingJ
 * @author RimmyC
 * @author SpencerH
 */
public class MenuController implements Initializable {
  
    private DatabaseUtil databaseUtil;
    private ChartData chartData;
    private User user;
    private FoodCatalog catalog;

    @FXML
    private Label userLabel;
    
    @FXML
    private Label calendarMode;
    @FXML
    private Label calendarWeight;
    @FXML
    private Label calendarCalGoal;
    @FXML
    private Label calendarCalAte;
    
    @FXML
    private AnchorPane calendarAnchor;
    
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
    //Need to seperate these better
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
    
    //Meals and Food Tab
    @FXML
    public Button addFoodButton;
    
    @FXML
    public Button addMealButton;
    
    @FXML
    public HBox mealFoodHBox;
    
    @FXML
    public VBox mealVBox;
    
    @FXML 
    public VBox foodVBox;
    
    @FXML
    public VBox addFoodVBox;
    
    @FXML
    public TextField foodNameText;
    
    @FXML
    public TextField foodCalText;
    
    @FXML
    public TextField foodFatText;
    
    @FXML
    public TextField foodCarbText;
    
    @FXML
    public TextField foodProteinText;
    
    @FXML
    public Button submitFoodButton;
    
    //Quick add 
    @FXML
    public Button quickAddButton;
            
    @FXML
    public TextField quickAddTextCalories;
    
    @FXML
    public TextField quickAddTextFat;
    
    @FXML
    public TextField quickAddTextCarbs;
    
    @FXML
    public TextField quickAddTextProtein;
    
    @FXML 
    public VBox quickAddVBox;
    
    @FXML
    public Button submitQuickAddButton;
    @FXML
    public TextField weighInField;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private Label nullDayLabel;
    
    @FXML
    private LineChart<String, Number> weightChart;
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
            user.initializeCalendar();
            chartData = new ChartData(user);
            if (user != null) {
                updateMainLabels();
                updateUser();
                updateMode();
                updateMacroLabels();
                setInitialLabels();
                
                updateCalProgressBar();
                updateFatProgressBar();
                updateCarbsProgressBar();
                updateProteinProgressBar();
                
                chartData.populateChartArray();
                createChart();
            }
            updatePieChart();
            
            if(!databaseUtil.getFoods().isEmpty()){
            catalog = new FoodCatalog(databaseUtil.getFoods());
            } else {
                catalog = new FoodCatalog();
            }
            
            for (int i = 0; i < catalog.size(); i++) {
                updateFood(catalog.getFood(i), false);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateScene() {
        updatePieChart();
        updateCalProgressBar();
        updateFatProgressBar();
        updateCarbsProgressBar();
        updateProteinProgressBar();
        updateMacroLabels();
    }
    /**
     * Updates pie chart with current remaining and consumed calories.
     */
    private void updatePieChart() {
        caloriesPieChart.setLegendVisible(false);
        caloriesPieChart.setLabelLineLength(15);
        
       
        int consumedCalories = user.getDay().getCalories();
        user.getDay().setRemainingCalories(consumedCalories);
        int remainingCalories = user.getDay().getRemainingCalories();
        
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
        proteinProgressBar.setProgress(proteinProgress); 
    }

    
    /**
     * Handles add food button
     * 
     * @param event triggered by add food button
     */
    @FXML
    void addFood(ActionEvent event) {
        mealFoodHBox.setVisible(false);
        addFoodVBox.setVisible(true);
    }
    
    /**
     * Handles the submit button for adding food
     * @param event triggered by submit button
     */
    @FXML
    void submitFood(ActionEvent event) throws SQLException {
       Food food = new Food(foodNameText.getText(), Integer.parseInt(foodCalText.getText()), Float.parseFloat(foodFatText.getText()),
               Float.parseFloat(foodCarbText.getText()), Float.parseFloat(foodProteinText.getText()));
       updateFood(food, true);
    }
    
    /**
     * updates UI when the submit food button is pressed
     * @param food 
     */
    @FXML
    void updateFood(Food food, boolean store) throws SQLException {
        Label name = new Label(food.getName());
        VBox vbox = new VBox(name);
        foodVBox.getChildren().add(vbox);
        mealFoodHBox.setVisible(true);
        addFoodVBox.setVisible(false);
        if(store) {
        databaseUtil.storeFood(food);
        }
    }
    
    @FXML 
    void quickAdd() {
       quickAddVBox.setVisible(!quickAddVBox.isVisible());
    }
       

    @FXML
    void weighIn() {
        try {
            
        float weight = Float.parseFloat(weighInField.getText());
        
        user.setWeight(weight);
        user.getDay().setWeight(weight);
        databaseUtil.checkIn(weight, user.getCalendar().getDate());
        
        weighInField.clear();
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @FXML
    private void submitQuickAdd() {
        user.getDay().intake(Integer.parseInt(quickAddTextCalories.getText()), 
               Float.parseFloat(quickAddTextFat.getText()), Float.parseFloat(quickAddTextCarbs.getText()), 
               Float.parseFloat(quickAddTextProtein.getText()), user.getCalendar().getDate());
        
        quickAddVBox.setVisible(!quickAddVBox.isVisible());
        
        quickAddTextCalories.clear();
        quickAddTextFat.clear();
        quickAddTextCarbs.clear();
        quickAddTextProtein.clear();
        setInitialLabels();
        updateScene();
    }
    
    @FXML
    private void handleDate(){
       LocalDate selectedDate = datePicker.getValue();
       if(selectedDate  != null){
           Date sqlDate = Date.valueOf(selectedDate);
           Day selectedDay = user.getCalendar().getDay(sqlDate);
           if(selectedDay == null)
           {
           calendarAnchor.setVisible(false);
           nullDayLabel.setVisible(true);
           }
           else
           {
           nullDayLabel.setVisible(false);
           calendarAnchor.setVisible(true);
           calendarMode.setText("Mode: " + selectedDay.getMode());
           calendarWeight.setText("Weight: " + selectedDay.getWeight());
           calendarCalGoal.setText("Calorie Goal: " + selectedDay.getCalorieGoal());
           calendarCalAte.setText("Calories Ate: " + selectedDay.getCalories());
           }
       }
    }
    
    private void createChart(){
        ArrayList<String> dates = chartData.getDates();
        ArrayList<Float> weights = chartData.getWeights();
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(int i = 0; i < dates.size(); i++){
            series.getData().add(new XYChart.Data<>(dates.get(i), weights.get(i)));
        }
        weightChart.getData().add(series);
    }
    /**
     * Updates users calories. Used in initialization to show goal.
     */
    private void updateMainLabels() {
        caloriesLabel.setText("Calorie Goal: " + (int) user.getDay().getCalorieGoal());
    }
    /**
     * Updates userMode
     */
    private void updateMode() {
        curModeLabel.setText(user.getModeAsString());
    }
    /**
     * Sets initialLabels for fat, carbs, protein, and calories.
     */
    private void setInitialLabels() {
        
        fatLabelStart.setText(String.valueOf(user.getDay().getFat()));
        carbLabelStart.setText(String.valueOf(user.getDay().getCarbs()));
        proteinLabelStart.setText(String.valueOf(user.getDay().getProtein()));
        calLabelStart.setText(String.valueOf(user.getDay().getCalories()));
    }
    /**
     * Updates MacroLabels
     */
    private void updateMacroLabels() {
        fatLabel.setText("Fat Goal:" + Integer.toString( (int) user.getDay().getFatGoal()));
        carbLabel.setText("Carb Goal: " + Integer.toString( (int) user.getDay().getCarbGoal()));
        proteinLabel.setText("Protein Goal: " + Integer.toString( (int) user.getDay().getProteinGoal()));
        calLabel.setText("Calorie Goal: " + Integer.toString((int) user.getDay().getCalorieGoal()));
    }
    /**
     * Updates greeting label after we retrieve user from database.
     */
    private void updateUser() {
        userLabel.setText("Hey, " + user.getUsername() + "!");
    }
}
