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
    public void showDetailScene(Stage primaryStage){
        
        // DUMMY CODE TO SHOW WE ARE IN DETAIL SCENE CAN DELETE
        Label dumbLabel = new Label("I am in Detail Scene!");
        StackPane root = new StackPane();
        root.getChildren().setAll(dumbLabel);
        
        //Sets new scene to stage keep this.
        Scene detailScene = new Scene(root);
        primaryStage.setScene(detailScene);
    }
}
