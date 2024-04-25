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
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import objects.ChartData;
import objects.Day;
import objects.Food;
import objects.FoodCatalog;
import objects.Meal;
import objects.MealCatalog;

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
    private MealCatalog mealCatalog;
    private double dontDeleteThis;
    
    // litterally have to make 4 rectangles because they cant have same id smh.
    @FXML
    private Rectangle rectangle; 
    @FXML
    private Rectangle rectangle2;
    @FXML
    private Rectangle rectangle3;
    @FXML
    private Rectangle rectangle4;
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
    
    private User.CurrentMode newMode;
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
    @FXML
    private Label nullDayLabel;
    //Meals and Food Tab
    @FXML
    public Button addFoodButton;
    @FXML
    public Button addMealButton;
     @FXML
    public Button submitFoodButton;
    @FXML
    public Button quickAddButton;
    @FXML
    public Button submitQuickAddButton;
    
    @FXML
    public HBox mealFoodHBox;
    @FXML
    public VBox mealVBox;
    @FXML
    public VBox foodVBox;
    @FXML
    public VBox addFoodVBox;
    @FXML
    public VBox quickAddVBox;

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
    public TextField quickAddTextCalories;
    @FXML
    public TextField quickAddTextFat;
    @FXML
    public TextField quickAddTextCarbs;
    @FXML
    public TextField quickAddTextProtein;
    
    //Meals
    @FXML
    public TextField mealNameText;
    
    @FXML
    public VBox foodMealVBox;
    
    @FXML
    public VBox addMealVBox;
    
    @FXML
    public Label mealNameDisplay;
    
    private ArrayList<Food> foods;
    
    private HBox displayMealFoods;
    
    @FXML
    public TextField weighInField;

    @FXML
    private DatePicker datePicker;
    @FXML
    private LineChart<String, Number> weightChart;
    @FXML
    private ToggleGroup chartToggleGroup;
    @FXML
    private ToggleGroup modeToggleGroup;
    @FXML
    private ToggleButton maintainButton;
    @FXML
    private ToggleButton cutButton;
    @FXML
    private ToggleButton bulkButton;
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
            //trying to fix pie chart.
      
             
            databaseUtil = new DatabaseUtil();
            user = databaseUtil.getUserDetails();
            user.initializeCalendar();
            chartData = new ChartData(user);
            if (user != null) {
                updateUser();
                setMacroLabels();
                
                modeToggleGroup = new ToggleGroup();
               maintainButton.setToggleGroup(modeToggleGroup);
               cutButton.setToggleGroup(modeToggleGroup);
               bulkButton.setToggleGroup(modeToggleGroup);
               updatePieChart();
                updateScene();
                createChart(6);
            }
            
            if (!databaseUtil.getFoods().isEmpty()) {
                catalog = new FoodCatalog(databaseUtil.getFoods());
            } else {
                catalog = new FoodCatalog();
            }
            
            for (int i = 0; i < catalog.size(); i++) {
                updateFood(catalog.getFood(i), false);
            }
            
            if (!databaseUtil.getMeals().isEmpty()) {
                mealCatalog = new MealCatalog(databaseUtil.getMeals());
            } else {
                mealCatalog = new MealCatalog();
            }
            
            for (int i = 0; i < mealCatalog.size(); i++) {
                updateMeal(mealCatalog.getMeal(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateScene() {
        String mode = user.getModeAsString().toUpperCase();
        curModeLabel.setText(mode);
         updateColors(mode);
        
        
     //   updatePieChart();
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
        //caloriesPieChart.setLabelLineLength(15);
        
        int consumedCalories = user.getDay().getCalories();
        
        user.getDay().setRemainingCalories(consumedCalories);
        int remainingCalories = user.getDay().getRemainingCalories();
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(remainingCalories + " Calories Remaining", remainingCalories),
                new PieChart.Data(consumedCalories + " Calories Consumed", consumedCalories)
        );
        caloriesPieChart.setData(pieChartData);
   
    }
    
    // dont ask, i need this because of a decade old java fx bug. Thank you jesus.
        private void updatePieChart2() {
        caloriesPieChart.setLegendVisible(false);
        //caloriesPieChart.setLabelLineLength(15);
        // LITTERALLY NO ONE NEEDS TO KNOW WHAT THIS DOES
        // grabbing the past pie chart data. this is an edge case that is too 
        //hard to explain and if unchecked will destroy the universe
        ObservableList<PieChart.Data> pieChartData = caloriesPieChart.getData();
        for (PieChart.Data data : pieChartData){
            if (data.getName().contains("Remaining")){
               dontDeleteThis = data.getPieValue();
            }
        }
     
        
        int consumedCalories = user.getDay().getCalories();
        
        user.getDay().setRemainingCalories(consumedCalories);
        int remainingCalories = user.getDay().getRemainingCalories();
        
        if(consumedCalories == 0){
             for(PieChart.Data data : pieChartData){
                 if(data.getName().contains("Remaining")){
                     data.setName(remainingCalories + " Calories Remaining");
                     return;
                 }
             }
        }
        else if(remainingCalories == 0 && dontDeleteThis == 0){
            return;
        }
       
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(remainingCalories + " Calories Remaining", remainingCalories),
                new PieChart.Data(consumedCalories + " Calories Consumed", consumedCalories)
        );
        caloriesPieChart.setData(pieChartData);
    }
        
   

   
    /**
     * Updates progress bar with current remaining and consumed calories.
     */
    @FXML
    public void updateCalProgressBar() {
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
     * Updates the ProteinProgressBar with current remaining and consumed
     * Protein
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
       caloriesPieChart.setLabelLineLength(10); // Set a shorter label line length
       
        mealFoodHBox.setVisible(false);
        addFoodVBox.setVisible(true);
    }

    /**
     * Handles the submit button for adding food
     *
     * @param event triggered by submit button
     */
    @FXML
    void submitFood(ActionEvent event) throws SQLException {
        Food food = new Food(foodNameText.getText(), Integer.parseInt(foodCalText.getText()), Float.parseFloat(foodFatText.getText()),
                Float.parseFloat(foodCarbText.getText()), Float.parseFloat(foodProteinText.getText()));
        
        foodNameText.clear();
        foodCalText.clear();
        foodFatText.clear();
        foodCarbText.clear();
        foodProteinText.clear();
        
        catalog.addFood(food);
        
        updateFood(food, true);
    }
    
    /**
     * method that handle when a user presses the cancel button
     * in the add food Menu
     * @param event 
     */
    @FXML
    void cancelFood(ActionEvent event) {
        foodNameText.clear();
        foodCalText.clear();
        foodFatText.clear();
        foodCarbText.clear();
        foodProteinText.clear();
        
        mealFoodHBox.setVisible(true);
        addFoodVBox.setVisible(false);
    }

    /**
     * updates UI when the submit food button is pressed
     *
     * @param food
     */
    @FXML
    void updateFood(Food food, boolean store) throws SQLException {
        Label name = new Label(food.getName());
        name.setAlignment(Pos.CENTER);
        name.setStyle("-fx-font: 16px Avenir;");
        
        Button eatBtn = new Button("Eat");
        eatBtn.setAlignment(Pos.CENTER);
        eatBtn.setPrefSize(60, 20);
        
        Button deleteBtn = new Button("Delete");
        deleteBtn.setAlignment(Pos.CENTER);
        deleteBtn.setPrefSize(60, 20);
        
        HBox foodHBox = new HBox(name, eatBtn, deleteBtn);
        foodHBox.setAlignment(Pos.CENTER);
        foodHBox.setSpacing(10);
        
        Label foodCal = new Label("Cal" + Integer.toString(food.getCalories()));
        Label foodF = new Label(" F" + Float.toString(food.getFat()));
        Label foodC = new Label(" C" + Float.toString(food.getCarbs()));
        Label foodP = new Label(" P" + Float.toString(food.getProtein()));
        
        HBox macroHBox = new HBox(foodCal, foodF, foodC, foodP);        
        macroHBox.setAlignment(Pos.CENTER);
        
        VBox vbox = new VBox(foodHBox, macroHBox);
        
        vbox.setAlignment(Pos.CENTER);
        
        FoodMap foodMap = new FoodMap(food, eatBtn, deleteBtn, vbox, this);
        
        foodVBox.getChildren().add(vbox);
        foodVBox.setSpacing(16);
        mealFoodHBox.setVisible(true);
        addFoodVBox.setVisible(false);
        
        if (store) {
            databaseUtil.storeFood(food);
        }
    }
    
    /**
     * Method to eat a food from the meal and food menu
     * 
     * @param food 
     */
    public void eatFood(Food food) {
        user.getDay().intake(food.getCalories(), food.getFat(), food.getCarbs(), food.getProtein(), user.getCalendar().getDate());
        setMacroLabels();
        updatePieChart();
        updateScene();
    }
    
    /**
     * Method that deletes a food from the meal and food menu, updates 
     * the food VBox, and deletes the food in the database;
     * 
     * @param food
     * @throws SQLException 
     */
    public void deleteFood(Food food, VBox vbox) throws SQLException {
        databaseUtil.deleteFood(food);
        
        catalog.removeFood(food);
        
        foodVBox.getChildren().remove(vbox);
       }
    
    /**
     * method that handles when a user presses the add meal button
     */
    @FXML
    void addMeal() {
        mealFoodHBox.setVisible(false);
        addMealVBox.setVisible(true);
        
        Meal meal = new Meal();
        
        for (int i = 0; i < catalog.size(); i++) {
        Label name = new Label(catalog.getFood(i).getName());
        name.setAlignment(Pos.CENTER);
        name.setStyle("-fx-font: 16px Avenir;");
        
        Button addBtn = new Button("Add");
        addBtn.setAlignment(Pos.CENTER);
        addBtn.setPrefSize(60, 20);
        
        Button removeBtn = new Button("Remove");
        removeBtn.setAlignment(Pos.CENTER);
        removeBtn.setPrefSize(60, 20);
        
        HBox foodHBox = new HBox(name, addBtn, removeBtn);
        foodHBox.setAlignment(Pos.CENTER);
        foodHBox.setSpacing(10);
        
        Label foodCal = new Label("Cal" + Integer.toString(catalog.getFood(i).getCalories()));
        Label foodF = new Label(" F" + Float.toString(catalog.getFood(i).getFat()));
        Label foodC = new Label(" C" + Float.toString(catalog.getFood(i).getCarbs()));
        Label foodP = new Label(" P" + Float.toString(catalog.getFood(i).getProtein()));
        
        HBox macroHBox = new HBox(foodCal, foodF, foodC, foodP);        
        macroHBox.setAlignment(Pos.CENTER);
        
        VBox vbox = new VBox(foodHBox, macroHBox);
        
        vbox.setAlignment(Pos.CENTER);
        
        AddToMealMap addToMealMap = new AddToMealMap(catalog.getFood(i), addBtn, removeBtn, this);
        
        foodMealVBox.getChildren().add(vbox);
        }
        
        displayMealFoods = new HBox();
        addMealVBox.getChildren().add(displayMealFoods);
        foods = new ArrayList<Food>();
    }
    
    /**
     * method that handles when a user presses the button to add a 
     * food item to a meal
     * @param food 
     */
    public void addToMeal(Food food) {
        foods.add(food);
        
        displayMealFoods.getChildren().clear();
        
        for (int i = 0; i < foods.size(); i++) {
            Label label = new Label(foods.get(i).getName() + " ");
            displayMealFoods.getChildren().add(label);
        }
    }
    
    /**
     * method that handles when a user presses the button to remove
     * a food item from a meal
     * @param food 
     */
    public void removeFromMeal(Food food) {
        foods.remove(food);
        
        displayMealFoods.getChildren().clear();
        
        for (int i = 0; i < foods.size(); i++) {
            Label label = new Label(foods.get(i).getName() + " ");
            displayMealFoods.getChildren().add(label);
        }
    }
    
    /**
     * method that handles when a user submits a meal and stores
     * it in the database
     * @throws SQLException 
     */
    @FXML
    void submitMeal() throws SQLException {
        Meal meal = new Meal();
        
        meal.setName(mealNameText.getText());
        
        for (int i = 0; i < foods.size(); i++) {
            meal.addFood(foods.get(i));
        }
        
        updateMeal(meal);
        
        databaseUtil.storeMeal(meal);
        
        mealNameText.clear();
        mealNameDisplay.setText("");
        foodMealVBox.getChildren().clear();
        displayMealFoods.getChildren().clear();
        foods.clear();
        
        mealFoodHBox.setVisible(true);
        addMealVBox.setVisible(false);
    }
    
    /**
     * method that handles when a user presses the cancel button 
     * to cancel a meal creation
     */
    @FXML
    void cancelMeal() {
        mealNameText.clear();
        mealNameDisplay.setText("");
        foodMealVBox.getChildren().clear();
        displayMealFoods.getChildren().clear();
        foods.clear();
        
        mealFoodHBox.setVisible(true);
        addMealVBox.setVisible(false);
    }
    
    /**
     * method that updates the text display of a meal name 
     * while a user enters the name of their meal
     */
    @FXML
    void updateMealNameDisplay() {
        mealNameDisplay.setText(mealNameText.getText());
    }

    /**
     * method that updates the list of meals on the app
     * @param meal 
     */
    @FXML
    void updateMeal(Meal meal) {
        Label name = new Label(meal.getName());
        name.setAlignment(Pos.CENTER);
        name.setStyle("-fx-font: 16px Avenir;");
        
        Button eatBtn = new Button("Eat");
        eatBtn.setAlignment(Pos.CENTER);
        eatBtn.setPrefSize(60, 20);
        
        Button deleteBtn = new Button("Delete");
        deleteBtn.setAlignment(Pos.CENTER);
        deleteBtn.setPrefSize(60, 20);
        
        HBox mealHBox = new HBox(name, eatBtn, deleteBtn);
        mealHBox.setAlignment(Pos.CENTER);
        mealHBox.setSpacing(10);
        
        Label mealCal = new Label("Cal" + Integer.toString(meal.getTotalCalories()));
        Label mealF = new Label(" F" + Float.toString(meal.getTotalFat()));
        Label mealC = new Label(" C" + Float.toString(meal.getTotalCarbs()));
        Label mealP = new Label(" P" + Float.toString(meal.getTotalProtein()));
        
        HBox macroHBox = new HBox(mealCal, mealF, mealC, mealP);        
        macroHBox.setAlignment(Pos.CENTER);
        
        VBox vbox = new VBox(mealHBox, macroHBox);
        
        vbox.setAlignment(Pos.CENTER);
        
        MealMap mealMap = new MealMap(meal, eatBtn, deleteBtn, vbox, this);
        
        mealVBox.getChildren().add(vbox);
        mealVBox.setSpacing(16);
        
        mealFoodHBox.setVisible(true);
        addMealVBox.setVisible(false);
    }
    
    /**
     * method that handles when a user presses the eat button 
     * on a given meal
     * @param meal 
     */
    public void eatMeal(Meal meal) {
        user.getDay().intake(meal.getTotalCalories(), meal.getTotalFat(), meal.getTotalCarbs(), meal.getTotalProtein(), user.getCalendar().getDate());
        setMacroLabels();
        updatePieChart();
        updateScene();
    }
    
    /**
     * method that handles when a user presses the delete button on a given meal
     * and removes it from the database
     * @param meal
     * @param vbox
     * @throws SQLException 
     */
    public void deleteMeal(Meal meal, VBox vbox) throws SQLException {
        databaseUtil.deleteMeal(meal);
        
        mealCatalog.removeMeal(meal);
        
        mealVBox.getChildren().remove(vbox);
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
            createChart(6);
            user.getDay().recalcMacros();
            updateScene();
            updatePieChart2();
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
        setMacroLabels();
        updatePieChart();
        updateScene();
    }

    @FXML
    private void handleDate() {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate != null) {
            Date sqlDate = Date.valueOf(selectedDate);
            Day selectedDay = user.getCalendar().getDay(sqlDate);
            if (selectedDay == null) {
                calendarAnchor.setVisible(false);
                nullDayLabel.setVisible(true);
            } else {
                nullDayLabel.setVisible(false);
                calendarAnchor.setVisible(true);
                calendarMode.setText("Mode: " + selectedDay.getMode());
                calendarWeight.setText("Weight: " + selectedDay.getWeight());
                calendarCalGoal.setText("Calorie Goal: " + selectedDay.getCalorieGoal());
                calendarCalAte.setText("Calories Ate: " + selectedDay.getCalories());
            }
        }
    }
    
    @FXML
    private void handleModeChange(){
        ToggleButton selectedButton = (ToggleButton) modeToggleGroup.getSelectedToggle();
        // check if they selected and they didnt select same one otherwise they be spamming.
        if(newMode != null && newMode != user.getCurrentMode()) {
            user.getDay().setCurrentMode(newMode);
            user.setCurrentMode(newMode);
            
            user.getDay().recalcMacros();
            updateScene();
            updatePieChart2();
            databaseUtil.changeMode(newMode, user.getCalendar().getDate());
        }
        
    }
        @FXML
    private void maintainButtonAction() {
        newMode = objects.User.CurrentMode.MAINTAIN;
    }

    @FXML
    private void cutButtonAction() {
        newMode = objects.User.CurrentMode.CUT;
    }

    @FXML
    private void bulkButtonAction() {
        newMode = objects.User.CurrentMode.BULK;
    }

    private void createChart(int numDays) {
        chartData.populateChartArray(numDays);
        weightChart.getData().clear();
        ArrayList<String> dates = chartData.getDates();
        ArrayList<Float> weights = chartData.getWeights();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < dates.size(); i++) {
            series.getData().add(new XYChart.Data<>(dates.get(i), weights.get(i)));
        }
        weightChart.getData().add(series);
    }
    
    // radio buttons for us to switch chart displays.
    @FXML
    private void switchTo7Days(ActionEvent event) {
        createChart(6);
    }

    @FXML
    private void switchTo30Days(ActionEvent event) {
        createChart(29);
    }

    /**
     * Sets current macros consumed for user.
     */
    private void setMacroLabels() {
        fatLabelStart.setText(String.valueOf(user.getDay().getFat()));
        carbLabelStart.setText(String.valueOf(user.getDay().getCarbs()));
        proteinLabelStart.setText(String.valueOf(user.getDay().getProtein()));
        calLabelStart.setText(String.valueOf(user.getDay().getCalories()));
    }

    /**
     * Updates MacroLabels
     */
    private void updateMacroLabels() {
        caloriesLabel.setText("Calorie Goal: " + (int) user.getDay().getCalorieGoal());
        fatLabel.setText("Fat Goal:" + Integer.toString((int) user.getDay().getFatGoal()));
        carbLabel.setText("Carb Goal: " + Integer.toString((int) user.getDay().getCarbGoal()));
        proteinLabel.setText("Protein Goal: " + Integer.toString((int) user.getDay().getProteinGoal()));
        calLabel.setText("Calorie Goal: " + Integer.toString((int) user.getDay().getCalorieGoal()));
    }

    /**
     * Updates greeting label after we retrieve user from database.
     */
    private void updateUser() {
        userLabel.setText("Hey, " + user.getUsername() + "!");
    }
    
    private void updateColors(String mode){
         if(mode.equals("CUT")){
            curModeLabel.setStyle("-fx-text-fill: #1dc41a; " + "-fx-font-weight: bold; " + "-fx-effect:  dropshadow(gaussian, rgba(0, 0, 0, 0.5), 0.2, 0, 0, 1)"); 
            rectangle.getStyleClass().clear(); 
            rectangle.getStyleClass().add("green-style");
            rectangle2.getStyleClass().clear(); 
            rectangle2.getStyleClass().add("green-style"); 
            rectangle3.getStyleClass().clear(); 
            rectangle3.getStyleClass().add("green-style"); 
            rectangle4.getStyleClass().clear(); 
            rectangle4.getStyleClass().add("green-style"); 
        }
        else if(mode.equals("MAINTAIN")){
            curModeLabel.setStyle("-fx-text-fill: #19c6c6; " + "-fx-font-weight: bold; " + "-fx-effect:  dropshadow(gaussian, rgba(0, 0, 0, 0.5), 0.2, 0, 0, 1)");
            rectangle.getStyleClass().clear(); 
            rectangle.getStyleClass().add("rectangleMenu");
            rectangle2.getStyleClass().clear(); 
            rectangle2.getStyleClass().add("rectangleMenu"); 
            rectangle3.getStyleClass().clear(); 
            rectangle3.getStyleClass().add("rectangleMenu"); 
            rectangle4.getStyleClass().clear(); 
            rectangle4.getStyleClass().add("rectangleMenu"); 
        }
        else {
            curModeLabel.setStyle("-fx-text-fill: #e80a0a; " + "-fx-font-weight: bold; " + "-fx-effect:  dropshadow(gaussian, rgba(0, 0, 0, 0.5), 0.2, 0, 0, 1)");
            rectangle.getStyleClass().clear(); 
            rectangle.getStyleClass().add("red-style");
            rectangle2.getStyleClass().clear(); 
            rectangle2.getStyleClass().add("red-style"); 
            rectangle3.getStyleClass().clear(); 
            rectangle3.getStyleClass().add("red-style"); 
            rectangle4.getStyleClass().clear(); 
            rectangle4.getStyleClass().add("red-style"); 
        }
    }
}
