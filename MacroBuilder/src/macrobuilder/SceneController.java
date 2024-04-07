/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import controllers.MenuController;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
/**
 * Controls scene switching
 *
 * @author KingJ
 */
public class SceneController {

    private final Stage primaryStage;
    private Scene registerScene;
    private Scene loginScene;
    private Scene detailScene;
    private Scene mainMenuScene;

    /**
     *  Sets primary stage for controller.
     * @param primaryStage 
     */
    public SceneController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Switches to change stages
    // Implemented lazy initialization
    // We check if scene doesnt exist and we create one at that moment.
    
    /**
     * Switches to registration scene.
     */
    public void switchToRegisterScene() {
        RegisterUserScene registerUserScene = new RegisterUserScene();
        registerScene = registerUserScene.createRegisterScene(primaryStage, this);
        primaryStage.setScene(registerScene);
    }

    /**
     * Switches to login scene.
     */
    public void switchToLoginScene() {
        LoginScene loginUserScene = new LoginScene();
        loginScene = loginUserScene.createLoginScene(primaryStage, this);
        primaryStage.setScene(loginScene);
    }
    /**
     * Switches to detail scene
     * Creates one if it doesn't exist.
     */
    public void switchToDetailScene() {
        if (detailScene == null) {
            DetailScene userDetailScene = new DetailScene();
            detailScene = userDetailScene.showDetailScene(primaryStage, this);
        }
        primaryStage.setScene(detailScene);
    }
    /**
     * Switches to menu scene.
     * Creates one if it doesn't exist.
     * @throws SQLException
     * @throws IOException 
     */
    public void switchToMenuScene() throws SQLException, IOException {
        if (mainMenuScene == null) {
         MainMenuScene mainScene = new MainMenuScene();
         mainMenuScene = mainScene.showMenuScene(primaryStage, this);
        }
        primaryStage.setScene(mainMenuScene);
    }

    // Getters for scenes
    // Check if scene is null, if so we create one and return it
    
    /**
     * Gets register scene.
     * Creates one if it doesn't exist.
     * @return registration scene.
     */
    public Scene getRegisterScene() {
        if (registerScene == null) {
            switchToRegisterScene();
        }
        return this.registerScene;
    }
    /**
     * Gets login scene.
     * Creates one if it doesn't exist.
     * @return login scene.
     */
    public Scene getLoginScene() {
        if (loginScene == null) {
            switchToLoginScene();
        }
        return this.loginScene;
    }
    /**
     * Gets detail scene.
     * Creates one if it doesn't exist.
     * @return detail scene.
     */
    public Scene getDetailScene() {
        if (detailScene == null) {
            switchToDetailScene();
        }
        return this.detailScene;
    }
    /**
     * Gets menu scene.
     * Creates one if it doesn't exist.
     * @return menu scene.
     * @throws SQLException
     * @throws IOException 
     */
    public Scene getMenuScene() throws SQLException, IOException {
        if (mainMenuScene == null) {
            switchToMenuScene();
        }
        return this.mainMenuScene;
    }
}
