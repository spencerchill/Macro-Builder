package macrobuilder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
@author Ibrah
@author KingJ
Initial setup of the main menu scene.(Will be importing images, changing sizes of labels etc.)
*/
public class MainMenuScene {

    public Scene showMenuScene(Stage primaryStage, SceneController sceneController) {
        Label titleLabel = new Label("Macro Builder Tracker");
        titleLabel.setTextFill(Color.WHITE);
        
        Label heightLabel = new Label("Height (cm):");
        TextField heightField = new TextField();
        
        Label weightLabel = new Label("Weight (kg):");
        TextField weightField = new TextField();
        
        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();
        
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(event -> {
            // Handle calculation here, you can access values from fields
            // For example:
            String heightText = heightField.getText();
            double height = Double.parseDouble(heightText);
            
            String weightText = weightField.getText();
            double weight = Double.parseDouble(weightText);
            
            String ageText = ageField.getText();
            int age = Integer.parseInt(ageText);
            
            // Perform calculations with height, weight, age
            // You can update the UI or perform other operations based on these values
        });
        
        VBox root = new VBox(10); // spacing between elements
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
            titleLabel, 
            heightLabel, heightField, 
            weightLabel, weightField, 
            ageLabel, ageField, 
            calculateButton
        );
        
        // Set background color
        root.setStyle("-fx-background-color: #000000;");

        Scene newScene = new Scene(root, 400, 300);
        return newScene;
    }
}
