/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author sweet
 */
public class RegisterUserScene {

    public Scene createRegisterScene(Stage primaryStage, SceneController sceneController) {
        Font labelFont = new Font("Helvetica", 26);
        Font fieldFont = new Font("Lato", 16);

        Label welcomeLabel = new Label("We're happy you're here. Create an account.");
        welcomeLabel.setFont(fieldFont);
        welcomeLabel.setStyle("-fx-text-fill: #355E3B;");
        //Create labels and fonts
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label(" Password: ");
        Label emailLabel = new Label("        Email: ");

        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        // Text fields for our username and password and sets font
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField emailField = new TextField();

        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        emailField.setFont(fieldFont);

        Button registerButton = new Button("Register");
        Hyperlink switchScene = new Hyperlink("Already have an account? Sign-in here!");
        switchScene.setStyle("-fx-text-fill: #355E3B;");

        VBox root = new VBox(20);

        HBox usernameBox = new HBox();
        usernameBox.getChildren().addAll(usernameLabel, usernameField);
        usernameBox.setAlignment(Pos.CENTER);

        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        HBox emailBox = new HBox();
        emailBox.getChildren().addAll(emailLabel, emailField);
        emailBox.setAlignment(Pos.CENTER);

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(registerButton, switchScene);
        buttonsBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(welcomeLabel, emailBox, usernameBox, passwordBox, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #C2A887;");
        Scene registerScene = new Scene(root);

        registerButton.setOnAction((ActionEvent event) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            sceneController.switchToLoginScene(username, password);
        });

        switchScene.setOnAction(e -> sceneController.switchToLoginScene("", ""));
        registrationKeyHandlers(usernameField, passwordField, emailField, registerButton);

        return registerScene;
    }

    private void registrationKeyHandlers(TextField usernameField, PasswordField passwordField, TextField emailField, Button registerButton) {
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
                registerButton.fire();
            }
        });
        emailField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                usernameField.requestFocus();
            }
        });
    }

}
