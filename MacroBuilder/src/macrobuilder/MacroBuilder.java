/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package macrobuilder;

import database.ApiClient;
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
import objects.Food;

/**
 *
 * @author spencerhill
 * @author KingJ
 * @author ibrah
 */
public class MacroBuilder extends Application {
    private ApiClient api;
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
        double maxWidth = primaryStage.getMaxWidth();
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        //First scene is loginUI
        SceneController sceneController = new SceneController(primaryStage);
        // Start with registerScene
        primaryStage.getIcons().add(favicon);
        primaryStage.setScene(sceneController.getRegisterScene());
        
        //I dont want people with big monitors to resize
        // Its gonna look so ugly.
        if(maxWidth > 1440){
            primaryStage.setResizable(false);
        }
        primaryStage.setTitle("Macro Builder");
        //primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
