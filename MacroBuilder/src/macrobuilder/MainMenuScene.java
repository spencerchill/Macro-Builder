/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author sweet
 */
public class MainMenuScene {

    public Scene showMenuScene(Stage primaryStage, SceneController sceneController) {
        Label startText = new Label("This is the menu");
        startText.setTextFill(Color.WHITE);
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(startText);
        //so you dont get flashbanged
        root.setStyle("-fx-background-color: #000000;");
        
        Scene newScene = new Scene(root, 200, 200);
        return newScene;
    }
}
