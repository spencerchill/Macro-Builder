/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package macrobuilder;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author spencerhill
 * @author KingJ
 * @author ibrah
 */
public class MacroBuilder extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        Image favicon = new Image("favicon-16x16.png");
        
        Screen screen = Screen.getPrimary();
       // Rectangle2D bounds = screen.getVisualBounds();
       // primaryStage.setX(bounds.getMinX());
      // primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        //First scene is loginUI
        SceneController sceneController = new SceneController(primaryStage);
        // Start with registerScene
        primaryStage.getIcons().add(favicon);
        primaryStage.setScene(sceneController.getRegisterScene());
        //change later on to false if i feel like it
        primaryStage.setResizable(true);
        primaryStage.setTitle("Macro Builder");
        //primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
