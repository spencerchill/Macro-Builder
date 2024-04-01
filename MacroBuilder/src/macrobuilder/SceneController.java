/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Allows switching between scenes.
 *
 * @author KingJ
 */
public class SceneController {

    private final Stage primaryStage;
    private Scene registerScene;
    private Scene loginScene;
    private Scene detailScene;
    private Scene mainMenuScene;

    public SceneController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Switches to change stages
    // Implemented lazy initialization
    // We check if scene doesnt exist and we create one at that moment.
    public void switchToRegisterScene() {
        RegisterUserScene registerUserScene = new RegisterUserScene();
        registerScene = registerUserScene.createRegisterScene(primaryStage, this);
        primaryStage.setScene(registerScene);
    }

    //For now we pass the entered username and password for fun, but we wont pass this later.
    public void switchToLoginScene() {
        LoginScene loginUserScene = new LoginScene();
        loginScene = loginUserScene.createLoginScene(primaryStage, this);
        primaryStage.setScene(loginScene);
    }

    public void switchToDetailScene(String username) {
        if (detailScene == null) {
            DetailScene userDetailScene = new DetailScene(username);
            detailScene = userDetailScene.showDetailScene(primaryStage, this);
        }
        primaryStage.setScene(detailScene);
    }

    public void switchToMenuScene(objects.User user) {
        if (mainMenuScene == null) {
            MainMenuScene menuScene = new MainMenuScene(user);
            mainMenuScene = menuScene.showMenuScene(primaryStage, this);
        }
        primaryStage.setScene(mainMenuScene);
    }

    // Getters for scenes
    // Check if scene is null, if so we create one and return it
    public Scene getRegisterScene() {
        if (registerScene == null) {
            switchToRegisterScene();
        }
        return this.registerScene;
    }

    public Scene getLoginScene() {
        if (loginScene == null) {
            switchToLoginScene();
        }
        return this.loginScene;
    }

    public Scene getDetailScene() {
        if (detailScene == null) {
            switchToDetailScene("");
        }
        return this.detailScene;
    }

    public Scene getMenuScene() {
        if (mainMenuScene == null) {
            switchToMenuScene(null);
        }
        return this.mainMenuScene;
    }
}
