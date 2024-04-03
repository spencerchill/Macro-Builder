package macrobuilder;

import database.DetailController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents a scene for entering user details. This scene allows the user to
 * input their gender, age, height, weight, activity level, and current mode
 * Upon submission, the entered details are used to create a new User object.
 *
 * @author ibrah
 * @author spencerhill
 */
public class DetailScene {

    //Fields
   private DetailController detailController;
    private objects.User.Gender gender;
    private objects.User.ActivityLevel activityLevel;
    private objects.User.CurrentMode mode;

    /**
     * Constructor to create a DetailScene object with the specified username.
     *
     * @param username The username of the user whose details are being entered.
     */
    public DetailScene() {
    
    }

    /**
     * Creates and displays the detail scene for entering user details.
     *
     * @param primaryStage The primary stage of the application.
     * @param sceneController The scene controller for managing scene
     * transitions.
     * @return The detail scene for entering user details.
     */
    //Not fully finished, will have to work on some css style eventually(if we do it)
    public Scene showDetailScene(Stage primaryStage, SceneController sceneController) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        // Title Label
        Label titleLabel = new Label("Please input your information below");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 24));
        titleLabel.setPadding(new Insets(10, 0, 10, 0));

        // Gender Section
        Label genderLabel = new Label("Gender: ");
        genderLabel.setTextFill(Color.WHITE);
        genderLabel.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 16));
        RadioButton maleButton = new RadioButton("Male");
        maleButton.setTextFill(Color.WHITE);
        RadioButton femaleButton = new RadioButton("Female");
        femaleButton.setTextFill(Color.WHITE);
        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);

        VBox genderBox = new VBox(5, genderLabel, maleButton, femaleButton);
        genderBox.setAlignment(Pos.CENTER);
        genderBox.setPadding(new Insets(20, 0, 20, 0));
        maleButton.setOnAction(e -> {
            this.gender = objects.User.Gender.MALE;
        });

        femaleButton.setOnAction(e -> {
            this.gender = objects.User.Gender.FEMALE;
        });

        // Age, Height, and Weight Section
        Label ageLabel = new Label("Age: ");
        ageLabel.setTextFill(Color.WHITE);
        ageLabel.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 16));
        TextField ageText = new TextField();
        ageText.setPrefWidth(100); // Set a fixed width for the text field

        Label heightLabel = new Label("Height: ");
        heightLabel.setTextFill(Color.WHITE);
        heightLabel.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 16));
        TextField heightText = new TextField();
        heightText.setPrefWidth(100); // Set a fixed width for the text field
        Label inchLabel = new Label("inches");
        inchLabel.setTextFill(Color.WHITE);
        inchLabel.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 16));

        Label weightLabel = new Label("Weight: ");
        weightLabel.setTextFill(Color.WHITE);
        weightLabel.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 16));
        TextField weightText = new TextField();
        weightText.setPrefWidth(100); // Set a fixed width for the text field
        Label lbsLabel = new Label("lbs");
        lbsLabel.setTextFill(Color.WHITE);
        lbsLabel.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 16));

        HBox ageHeightWeightBox = new HBox(20,
                new VBox(5, ageLabel, heightLabel, weightLabel),
                new VBox(5, ageText, heightText, weightText),
                new VBox(5, new Label(), inchLabel, lbsLabel));
        ageHeightWeightBox.setAlignment(Pos.CENTER);
        
        //Make sure everything is aligned 
        ageLabel.setPadding(new Insets(2,0,2,0));
        heightLabel.setPadding(new Insets(2,0,2,0));
        heightText.setPadding(new Insets(2,0,2,0));
        inchLabel.setPadding(new Insets(5,0,2,0));
        lbsLabel.setPadding(new Insets(5,0,2,0));

        // Activity Level Section
        Label activityLabel = new Label("Activity Level: ");
        activityLabel.setTextFill(Color.WHITE);
        activityLabel.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 16));
        RadioButton notActive = new RadioButton("Not Active");
        notActive.setTextFill(Color.WHITE);
        RadioButton moderatelyActive = new RadioButton("Moderately Active");
        moderatelyActive.setTextFill(Color.WHITE);
        RadioButton active = new RadioButton("Active");
        active.setTextFill(Color.WHITE);

        ToggleGroup activityGroup = new ToggleGroup();

        notActive.setToggleGroup(activityGroup);
        moderatelyActive.setToggleGroup(activityGroup);
        active.setToggleGroup(activityGroup);

        // Set fixed width for radio buttons
        notActive.setMinWidth(150);
        moderatelyActive.setMinWidth(150);
        active.setMinWidth(150);

        VBox activityBox = new VBox(5, activityLabel, notActive, moderatelyActive, active);
        activityBox.setAlignment(Pos.CENTER);
        activityBox.setPadding(new Insets(20, 0, 20, 0));

        notActive.setOnAction(e -> {
            this.activityLevel = objects.User.ActivityLevel.NOT_ACTIVE;
        });

        moderatelyActive.setOnAction(e -> {
            this.activityLevel = objects.User.ActivityLevel.MODERATELY_ACTIVE;
        });

        active.setOnAction(e -> {
            this.activityLevel = objects.User.ActivityLevel.ACTIVE;
        });

        // Mode Section 
        Label modeLabel = new Label("Mode:");
        modeLabel.setTextFill(Color.WHITE);
        modeLabel.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 16));
        RadioButton cutButton = new RadioButton("Cut");
        cutButton.setTextFill(Color.WHITE);
        RadioButton maintainButton = new RadioButton("Maintain");
        maintainButton.setTextFill(Color.WHITE);
        RadioButton bulkButton = new RadioButton("Bulk");
        bulkButton.setTextFill(Color.WHITE);

        ToggleGroup modeGroup = new ToggleGroup();

        cutButton.setToggleGroup(modeGroup);
        maintainButton.setToggleGroup(modeGroup);
        bulkButton.setToggleGroup(modeGroup);

        VBox modeBox = new VBox(5, modeLabel, cutButton, maintainButton, bulkButton);
        modeBox.setAlignment(Pos.CENTER);
        modeBox.setPadding(new Insets(20, 0, 20, 0));

        cutButton.setOnAction(e -> {
            this.mode = objects.User.CurrentMode.CUT;
        });

        maintainButton.setOnAction(e -> {
            this.mode = objects.User.CurrentMode.MAINTAIN;
        });

        bulkButton.setOnAction(e -> {
            this.mode = objects.User.CurrentMode.BULK;
        });

        // Set fixed width for radio buttons
        maleButton.setMinWidth(100);
        femaleButton.setMinWidth(100);
        cutButton.setMinWidth(100);
        maintainButton.setMinWidth(100);
        bulkButton.setMinWidth(100);

        // Submit Button passes into main menu scene
        Button submitButton = new Button("Submit");
        submitButton.setFont(Font.font("ROCKWELL", FontWeight.BOLD, 16));
        submitButton.setTextFill(Color.WHITE);
        submitButton.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, new CornerRadii(5), Insets.EMPTY)));
        submitButton.setPadding(new Insets(10));
        submitButton.setOnAction(e -> {
            try {
                detailController = new DetailController();
                
                detailController.storeUserDetails(this.gender, Integer.parseInt(ageText.getText()),
                        Float.parseFloat(heightText.getText()), Float.parseFloat(weightText.getText()), this.activityLevel, this.mode);
                
                sceneController.switchToMenuScene();
                
            } catch (IOException | SQLException ex) {
                Logger.getLogger(LoginScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        root.getChildren().addAll(titleLabel, genderBox, ageHeightWeightBox, activityBox, modeBox, submitButton);

        Scene detailScene = new Scene(root);
        return detailScene;
    }
}
