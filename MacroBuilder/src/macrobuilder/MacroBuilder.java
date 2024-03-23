/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package macrobuilder;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author spencerhill
 * @author KingJ
 * @author rimmycara (idk your author name change this)
 */
public class MacroBuilder extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        //First scene is loginUI
        LoginScene loginScene = new LoginScene();
        primaryStage.setScene(loginScene.createLoginScene(primaryStage));
        primaryStage.setTitle("Macro Builder");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    private void showDetailScene(Stage primaryStage) {

        // DUMMY CODE TO SHOW WE ARE IN DETAIL SCENE CAN DELETE
        Label dumbLabel = new Label("I am in Detail Scene!");
        StackPane root = new StackPane();
        root.getChildren().setAll(dumbLabel);
        //Sets new scene to stage keep this to make it easier for you
        Scene detailScene = new Scene(root);
        primaryStage.setScene(detailScene);
    }
}
