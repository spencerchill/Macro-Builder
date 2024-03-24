/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Allows switching between scenes.
 * @author KingJ
 */
public class SceneController {
    private final Stage primaryStage;
    private Scene registerScene ;
    private Scene loginScene;
    private DetailScene detailScene;
    
    // initializes all scenes
    public SceneController(Stage primaryStage) {
       this. primaryStage = primaryStage;
       createScenes();
    }
    public void createScenes() {
        RegisterUserScene registerUserScene = new RegisterUserScene();
        LoginScene loginScene = new LoginScene();
        DetailScene detailScene = new DetailScene("");

        this.registerScene = registerUserScene.createRegisterScene(primaryStage, this);
        this.loginScene = loginScene.createLoginScene(primaryStage, this);
        this.detailScene = detailScene;
    }
    
    public void switchToRegisterScene() {
        primaryStage.setScene(registerScene);
    }
    
    //For now we pass the entered username and password for fun, but we wont pass this later.
    public void switchToLoginScene(String username, String password) {
        LoginScene loginScene = new LoginScene (username, password);
       this.loginScene = loginScene.createLoginScene(primaryStage, this);
        primaryStage.setScene(this.loginScene);
    }
    
    public void switchToDetailScene(String username){
        DetailScene detailScene = new DetailScene(username);
        this.detailScene = detailScene;
       this.detailScene.showDetailScene(primaryStage);
    }
    
    public Scene getRegisterScene() {
        return this.registerScene;
    }
    
    public Scene getLoginScene(){
        return this.loginScene;
    }
    
    public DetailScene getDetailScene() {
        return this.detailScene;
    }
}
