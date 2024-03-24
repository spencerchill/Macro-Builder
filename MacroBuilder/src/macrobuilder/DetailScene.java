/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author KingJ
 * @author ibrah
 */
public class DetailScene {

    private final String username;

    // Transfers username from login scene to detail scene
    public DetailScene(String username) {
        this.username = username;
    }

    public Scene showDetailScene(Stage primaryStage, SceneController sceneController) {

        // DUMMY CODE TO SHOW WE ARE IN DETAIL SCENE CAN DELETE
        // Now we can save username from login scene and when we get all details, create user object!
        Button dumbButton = new Button("switch to menu " + username);
        dumbButton.setStyle("-fx-text-fill: #FDAE44;");
        StackPane root = new StackPane();
        root.getChildren().setAll(dumbButton);
        root.setStyle("-fx-background-color: #333333;");

        dumbButton.setOnAction((ActionEvent event) -> {
            sceneController.switchToMenuScene();
        });
        //Sets new scene to stage keep this.
        Scene detailScene = new Scene(root);
        return detailScene;
    }
}
