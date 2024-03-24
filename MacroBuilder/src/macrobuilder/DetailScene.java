          /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author KingJ
 * @author rimmycara
 */
public class DetailScene {

    private final String username;

    // Transfers username from login scene to detail scene
    public DetailScene(String username) {
        this.username = username;
    }

    public void showDetailScene(Stage primaryStage) {

        // DUMMY CODE TO SHOW WE ARE IN DETAIL SCENE CAN DELETE
        // Now we can save username from login scene and when we get all details, create user object!
        Label dumbLabel = new Label("I am in Detail Scene! Hello " + username + "!");
        dumbLabel.setStyle("-fx-text-fill: #FDAE44;");
        StackPane root = new StackPane();
        root.getChildren().setAll(dumbLabel);
        //Simple background color we will create style sheets later can remove
        root.setStyle("-fx-background-color: #333333;");
        //Sets new scene to stage keep this.
        Scene detailScene = new Scene(root);
        primaryStage.setScene(detailScene);
    }
}
