/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import database.DatabaseUtil;
import database.UserManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * View for the login scene.
 * @author KingJ
 */
public class LoginScene {
   
    /**
     * Creates login GUI.
     * @param primaryStage
     * @param sceneController
     * @return login scene
     */
    public Scene createLoginScene(Stage primaryStage, SceneController sceneController) {

        Font labelFont = new Font("Helvetica", 26);
        Font fieldFont = new Font("Lato", 16);
        Font titleFont = new Font("impact", 50);
        //image for app
        Image image = new Image("orange.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        //Create labels and fonts
        Label titleLabel = new Label("Macro-Builder ");
        titleLabel.setFont(titleFont);
        titleLabel.setStyle("-fx-text-fill: #F6EEE3;");

        Label loginLabel = new Label("User Login");
        loginLabel.setFont(labelFont);
        loginLabel.setStyle("-fx-text-fill: #F6EEE3;");

        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        usernameLabel.setFont(labelFont);
        usernameLabel.setStyle("-fx-text-fill: #FDAE44;");
        passwordLabel.setFont(labelFont);
        passwordLabel.setStyle("-fx-text-fill: #FDAE44;");
        // Text fields for our username and password and sets font
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);

        // Create empty result label if we get it wrong, set text and paints it red
        Label resultLabel = new Label("");
        resultLabel.setFont(fieldFont);
        resultLabel.setStyle("-fx-text-fill: #D7504D;");

        //Creates a button with text "Login" and sets to same size as labels
        Button loginButton = new Button("Login");
        loginButton.setFont(fieldFont);

        Hyperlink switchScene = new Hyperlink("Don't have an account? Create one here!");
        switchScene.setStyle("-fx-text-fill: #FDAE44;");
        switchScene.setFont(fieldFont);
        // The idea is to stack both Horizontal Boxes verticaly inside of our vertical box
        VBox root = new VBox(20);

        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(titleLabel, imageView);
        titleBox.setAlignment(Pos.CENTER);
        HBox usernameBox = new HBox();
        usernameBox.getChildren().addAll(usernameLabel, usernameField);
        usernameBox.setAlignment(Pos.CENTER);

        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(loginButton, switchScene);
        buttonsBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleBox, loginLabel, resultLabel, usernameBox, passwordBox, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #333333;");

        Scene loginScene = new Scene(root);

        // Transitions to detail scene if correct. sets text in result if not.
        loginButton.setOnAction((ActionEvent event) -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            //Attempts login
            try {
                DatabaseUtil loginController = new DatabaseUtil();
                if (loginController.loginUser(username, password)) {
                   UserManager userManager = UserManager.getInstance();
                   userManager.setUserIId(loginController.getUserId(username));
                   userManager.setUsername(username);
                    if (loginController.firstLogin(username)) {
                        sceneController.switchToDetailScene();
                    } else {
                        sceneController.switchToMenuScene();
                    }

                } else {
                    resultLabel.setText("Incorrect Username and/or Password");
                }
            } catch (IOException | SQLException ex) {
                Logger.getLogger(LoginScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        switchScene.setOnAction((ActionEvent event) -> {
            sceneController.switchToRegisterScene();
        });

        loginRegistrationKeyHandlers(usernameField, passwordField, loginButton);

        return loginScene;
    }

    private void loginRegistrationKeyHandlers(TextField usernameField, PasswordField passwordField, Button loginButton) {
        // Event handlers that allow the user to press enter inside of form
        // Pressing enter on usernameField switches focus to the passwordField
        usernameField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                passwordField.requestFocus();
            }
        });
        // Pressing enter on passwordField presses the loginButton
        passwordField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                loginButton.fire();
            }
        });
    }

}
