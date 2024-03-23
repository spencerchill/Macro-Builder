/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * @author KingJ
 */
public class LoginScene {

    // feel free to change these later on to 1, 1 so we can get in quick.
    private final String CORRECT_USERNAME = "Jayden";
    private final String CORRECT_PASSWORD = "IsTheBest";

    public Scene createLoginScene(Stage primaryStage) {

        Font labelFont = new Font("Helvetica", 26);
        Font fieldFont = new Font("Arial", 16);
        //Create labels and fonts
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        // Text fields for our username and password and sets font
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);

        // Create empty result label if we get it wrong, set text and paints it red
        Label resultLabel = new Label("");
        resultLabel.setFont(labelFont);
        resultLabel.setTextFill(Color.RED);

        //Creates a button with text "Login" and sets to same size as labels
        Button loginButton = new Button("Login");
        loginButton.setFont(labelFont);

        // The idea is to stack both Horizontal Boxes verticaly inside of our vertical box
        VBox root = new VBox(20);

        HBox usernameBox = new HBox();
        usernameBox.getChildren().addAll(usernameLabel, usernameField);
        usernameBox.setAlignment(Pos.CENTER);

        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(resultLabel, usernameBox, passwordBox, loginButton);
        root.setAlignment(Pos.CENTER);

        Scene loginScene = new Scene(root);

        loginButton.setOnAction((ActionEvent event) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // if it equals our correct variables we transition to Detail Scene
            if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
                new DetailScene().showDetailScene(primaryStage);
            } else {
                //Set text in result label
                resultLabel.setText("Incorrect Username and/or Password");
            }
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
