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
    
    private objects.User user;

    public MainMenuScene (objects.User user) {
        this.user = user;
    }
    
    public Scene showMenuScene(Stage primaryStage, SceneController sceneController) {
        VBox root = new VBox(10);
        
        Label ageLabel = new Label(Integer.toString(user.getAge()));

        root.getChildren().addAll(ageLabel);
        Scene newScene = new Scene(root);
        return newScene;
    }
}
