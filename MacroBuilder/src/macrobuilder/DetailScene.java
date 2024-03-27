package macrobuilder;

import objects.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailScene {

    private final String username;
    private objects.User.Gender gender;
    private objects.User.ActivityLevel activityLevel;
    private objects.User.CurrentMode mode;

    public DetailScene(String username) {
        this.username = username;
    }

    public Scene showDetailScene(Stage primaryStage, SceneController sceneController) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Title Label
        Label titleLabel = new Label("Details");
        titleLabel.setStyle("-fx-font-size: 24px;");

        // Gender Section
        Label genderLabel = new Label("Gender: ");
        RadioButton maleButton = new RadioButton("Male");
        RadioButton femaleButton = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);

        VBox genderBox = new VBox(5, genderLabel, maleButton, femaleButton);
        genderBox.setAlignment(Pos.CENTER);

        maleButton.setOnAction(e -> {
            this.gender = objects.User.Gender.Male;
        });

        femaleButton.setOnAction(e -> {
            this.gender = objects.User.Gender.Female;
        });

        // Age Section
        Label ageLabel = new Label("Age: ");
        TextField ageText = new TextField();

        HBox ageBox = new HBox(10, ageLabel, ageText);
        ageBox.setAlignment(Pos.CENTER);

        // Height Section
        Label heightLabel = new Label("Height: ");
        TextField heightText = new TextField();
        Label inchLabel = new Label("inches");

        HBox heightBox = new HBox(10, heightLabel, heightText, inchLabel);
        heightBox.setAlignment(Pos.CENTER);

        // Weight Section
        Label weightLabel = new Label("Weight: ");
        TextField weightText = new TextField();
        Label lbsLabel = new Label("lbs");

        HBox weightBox = new HBox(10, weightLabel, weightText, lbsLabel);
        weightBox.setAlignment(Pos.CENTER);

        // Activity Level Section
        Label activityLabel = new Label("Activity Level: ");
        RadioButton notActive = new RadioButton("Not Active");
        RadioButton moderatelyActive = new RadioButton("Moderately Active");
        RadioButton active = new RadioButton("Active");

        ToggleGroup activityGroup = new ToggleGroup();

        notActive.setToggleGroup(activityGroup);
        moderatelyActive.setToggleGroup(activityGroup);
        active.setToggleGroup(activityGroup);

        VBox activityBox = new VBox(5, activityLabel, notActive, moderatelyActive, active);
        activityBox.setAlignment(Pos.CENTER);

        notActive.setOnAction(e -> {
            this.activityLevel = objects.User.ActivityLevel.NOT_ACTIVE;
        });

        moderatelyActive.setOnAction(e -> {
            this.activityLevel = objects.User.ActivityLevel.MODERATLY_ACTIVE;
        });

        active.setOnAction(e -> {
            this.activityLevel = objects.User.ActivityLevel.ACTIVE;
        });

        // Mode Section
        Label modeLabel = new Label("Mode:");
        RadioButton cutButton = new RadioButton("Cut");
        RadioButton maintainButton = new RadioButton("Maintain");
        RadioButton bulkButton = new RadioButton("Bulk");

        ToggleGroup modeGroup = new ToggleGroup();

        cutButton.setToggleGroup(modeGroup);
        maintainButton.setToggleGroup(modeGroup);
        bulkButton.setToggleGroup(modeGroup);

        VBox modeBox = new VBox(5, modeLabel, cutButton, maintainButton, bulkButton);
        modeBox.setAlignment(Pos.CENTER);

        cutButton.setOnAction(e -> {
            this.mode = objects.User.CurrentMode.CUT;
        });

        maintainButton.setOnAction(e -> {
            this.mode = objects.User.CurrentMode.MAINTAIN;
        });

        bulkButton.setOnAction(e -> {
            this.mode = objects.User.CurrentMode.BULK;
        });

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setAlignment(Pos.CENTER);

        submitButton.setOnAction(e -> {
            User newUser = new User(this.username, this.gender, Integer.parseInt(ageText.getText()),
                    Float.parseFloat(heightText.getText()), Float.parseFloat(weightText.getText()), this.activityLevel, this.mode);

            sceneController.switchToMenuScene(newUser);
        });

        root.getChildren().addAll(titleLabel, genderBox, ageBox, heightBox, weightBox, activityBox, modeBox, submitButton);

        Scene detailScene = new Scene(root);
        return detailScene;
    }
}
