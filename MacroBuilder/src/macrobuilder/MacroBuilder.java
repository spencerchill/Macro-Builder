/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package macrobuilder;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
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
        SceneController sceneController = new SceneController(primaryStage);
        // Start with registerScene
        primaryStage.setScene(sceneController.getRegisterScene());
        primaryStage.setTitle("Macro Builder");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
