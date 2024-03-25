/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import objects.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author KingJ
 * @author ibrah
 */
public class DetailScene {

    private final String username;
    private objects.User.Gender gender;
    private objects.User.ActivityLevel activityLevel;
    private objects.User.CurrentMode mode;

    // Transfers username from login scene to detail scene
    public DetailScene(String username) {
        this.username = username;
    }

    public Scene showDetailScene(Stage primaryStage, SceneController sceneController) {

        //creating root
        VBox root = new VBox();
        
        //creating detail label
        Label detailLabel = new Label("Details");
        
        //Creating Gender label
        Label genderLabel = new Label("Gender: ");
        RadioButton maleButton = new RadioButton("Male");
        RadioButton femaleButton = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);
        
        HBox genderBox = new HBox(genderLabel, maleButton, femaleButton);
        genderBox.setAlignment(Pos.CENTER);
        genderBox.setSpacing(10);
        
        maleButton.setOnAction(e -> {
            this.gender = objects.User.Gender.Male;
        });
        
        femaleButton.setOnAction(e -> {
            this.gender = objects.User.Gender.Female;
        });
        
        //creating age box
        Label ageLabel = new Label("Age: ");
        TextField ageText = new TextField();
        
        HBox ageBox = new HBox(ageLabel, ageText);
        ageBox.setAlignment(Pos.CENTER);
        ageBox.setSpacing(10);
        
        //creating height box
        Label heightLabel = new Label("Height: ");
        Label inchLabel = new Label("inches");
        TextField heightText = new TextField();
        
        HBox heightBox = new HBox(heightLabel, heightText, inchLabel);
        heightBox.setAlignment(Pos.CENTER);
        heightBox.setSpacing(10);
        
        //creating weight Box
        Label weightLabel = new Label("Weight: ");
        Label lbsLabel = new Label("lbs");
        TextField weightText= new TextField();
        
        HBox weightBox = new HBox(weightLabel, weightText, lbsLabel);
        weightBox.setAlignment(Pos.CENTER);
        weightBox.setSpacing(10);
        
        //creating activity box
        Label activityLabel = new Label("Activity Level: ");
        RadioButton notActive = new RadioButton("Not Active");
        RadioButton moderatlyActive = new RadioButton("Moderatly Active");
        RadioButton active = new RadioButton("Active");
        
        ToggleGroup activityGroup = new ToggleGroup();
        
        notActive.setToggleGroup(activityGroup);
        moderatlyActive.setToggleGroup(activityGroup);
        active.setToggleGroup(activityGroup);
        
        HBox activityBox = new HBox(activityLabel, notActive, moderatlyActive, active);
        activityBox.setAlignment(Pos.CENTER);
        activityBox.setSpacing(10);
        
        notActive.setOnAction(e-> {
            this.activityLevel = objects.User.ActivityLevel.NOT_ACTIVE;
        });
        
        moderatlyActive.setOnAction(e-> {
            this.activityLevel = objects.User.ActivityLevel.MODERATLY_ACTIVE;
        });
        
        active.setOnAction(e-> {
            this.activityLevel = objects.User.ActivityLevel.ACTIVE;
        });
        
        //creating mode box
        Label modeLabel = new Label("Mode:");
        RadioButton cutButton = new RadioButton("Cut");
        RadioButton maintainButton = new RadioButton("Maintain");
        RadioButton bulkButton = new RadioButton("Bulk");
        
        ToggleGroup modeGroup = new ToggleGroup();
        
        cutButton.setToggleGroup(modeGroup);
        maintainButton.setToggleGroup(modeGroup);
        bulkButton.setToggleGroup(modeGroup);
        
        HBox modeBox = new HBox(modeLabel, cutButton, maintainButton, bulkButton);
        modeBox.setAlignment(Pos.CENTER);
        modeBox.setSpacing(10);
        
        cutButton.setOnAction(e -> {
            this.mode = objects.User.CurrentMode.CUT;
        });
        
        maintainButton.setOnAction(e -> {
            this.mode = objects.User.CurrentMode.MAINTAIN;
        });
        
        bulkButton.setOnAction(e -> {
            this.mode = objects.User.CurrentMode.BULK;
        });
        
        //creating submit Button
        Button submitButton = new Button("Submit");
        submitButton.setAlignment(Pos.CENTER);
        
        submitButton.setOnAction(e -> {
            User newUser = new User(this.username, this.gender, Integer.parseInt(ageText.getText()), 
                    Float.parseFloat(heightText.getText()), Float.parseFloat(weightText.getText()), this.activityLevel, this.mode);
            
            sceneController.switchToMenuScene(newUser);
        });
        
        root.getChildren().addAll(detailLabel, genderBox, ageBox, heightBox, weightBox, activityBox, modeBox, submitButton);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        
        Scene detailScene = new Scene(root);
        return detailScene;
    }
}
