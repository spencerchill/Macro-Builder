/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macrobuilder;

import java.sql.SQLException;
import database.RegistrationController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
 *
 * @author sweet
 */
public class RegisterUserScene {
    
    private RegistrationController registrationController;

    public Scene createRegisterScene(Stage primaryStage, SceneController sceneController) {
        Font labelFont = new Font("Helvetica", 26);
        Font fieldFont = new Font("Lato", 20);
        Font titleFont = new Font("impact", 50);

        Image image = new Image("orange.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        Label titleLabel = new Label("Macro-Builder ");
        titleLabel.setFont(titleFont);
        titleLabel.setStyle("-fx-text-fill: #F6EEE3;");

        Label welcomeLabel = new Label("We're happy you're here. Create an account.");
        welcomeLabel.setFont(fieldFont);
        welcomeLabel.setStyle("-fx-text-fill: #F6EEE3;");
        //Create labels and fonts
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label(" Password: ");
        Label emailLabel = new Label("        Email: ");

        usernameLabel.setFont(labelFont);
        usernameLabel.setStyle("-fx-text-fill: #FDAE44;");
        passwordLabel.setFont(labelFont);
        passwordLabel.setStyle("-fx-text-fill: #FDAE44;");
        emailLabel.setFont(labelFont);
        emailLabel.setStyle("-fx-text-fill: #FDAE44;");
        // Text fields for our username and password and sets font
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField emailField = new TextField();

        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        emailField.setFont(fieldFont);

        Button registerButton = new Button("Register");
        Hyperlink switchScene = new Hyperlink("Already have an account? Sign-in here!");
        switchScene.setStyle("-fx-text-fill: #FDAE44;");
        switchScene.setFont(fieldFont);

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

        HBox emailBox = new HBox();
        emailBox.getChildren().addAll(emailLabel, emailField);
        emailBox.setAlignment(Pos.CENTER);

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(registerButton, switchScene);
        buttonsBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleBox, welcomeLabel, emailBox, usernameBox, passwordBox, buttonsBox);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #333333;");
        Scene registerScene = new Scene(root);
        
        // lets user know username doesnt meet required length
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValidUsername(newValue)) {
                usernameField.setStyle("-fx-border-color: green;");
            } else {
                usernameField.setStyle("-fx-border-color: red;");
            }
        });

        registerButton.setOnAction((ActionEvent event) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (!isValidUsername(username)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Username");
                alert.setHeaderText(null);
                alert.setContentText("Username must be between 5 and 15 characters long and contain only letters, numbers, and underscores.");
                alert.showAndWait();
                return;
            }
            
            try {
                registrationController = new RegistrationController();
                registrationController.registerUser(username, password);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(RegisterUserScene.class.getName()).log(Level.SEVERE, null, ex);
            }
            sceneController.switchToDetailScene(username);
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
    
    // checks if username is between 5 ann 15 characters and no special characters.
    public static boolean isValidUsername(String username) {
        int length = username.length();
        if (length < 5 || length > 15) {
            return false;
        }
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z0-9])[a-zA-Z0-9_]+$");
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }

}
