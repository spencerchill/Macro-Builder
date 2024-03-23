/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package macrobuilder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author spencerhill
 * @author KingJ
 * @author rimmycara (idk your author name change this)
 */
public class MacroBuilder extends Application {

    //Test variables for correct username and password.
    private final String USERNAME = "Jayden";
    private final String PASSWORD = "IsTheBest";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Sets font type and size for labels and fields
        Font labelFont = new Font("Helvetica", 26);
        Font fieldFont = new Font("Arial", 16);

        //Create username label and set font size.
        Label usernameLabel = new Label("Username: ");
        usernameLabel.setFont(labelFont);

        //Create password label and set font size.
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setFont(labelFont);

        // Create empty result label if we get it wrong, set text and paints it red
        Label resultLabel = new Label("");
        resultLabel.setFont(labelFont);
        resultLabel.setTextFill(Color.RED);

        // Text fields for our username and password and sets font
        TextField usernameField = new TextField();
        // Password field doesnt allow user to see the text they type, cool !!!
        PasswordField passwordField = new PasswordField();

        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);

        //Creates a button with text "Login" and sets to same size as labels
        Button loginButton = new Button("Login");
        loginButton.setFont(labelFont);

        //Get username and password and check if it matches our final variables (TEST DEMO)
        // If you are working on other menu scenes feel free to change the username and password 
        // to something easy to type.
        loginButton.setOnAction((ActionEvent event) -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            // if it equals our correct variables we transition to Detail Scene
            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                showDetailScene(primaryStage);
            } //Set text in result label
            else {
                resultLabel.setText("Login unsuccessful!");
            }
        });
        
        // Event handlers that allow the user to press enter inside of form
        // Pressing enter on usernameField switches focus to the passwordField
        usernameField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                passwordField.requestFocus();
            }
        });
        
        // Pressing enter on passwordField presses the loginButton
        passwordField.setOnKeyPressed(e -> {
                    if(e.getCode() == KeyCode.ENTER) {
                        loginButton.fire();
                    }
                });
        
        // Create VBox with vertical spacing of 20 pixels
        VBox root = new VBox(20);

        // Create Horizontal boxes for both username and password.
        // The idea is to stack both Horizontal Boxes verticaly inside of our vertical box
        HBox usernameBox = new HBox();
        //Add label and field
        usernameBox.getChildren().addAll(usernameLabel, usernameField);
        //positions box in center
        usernameBox.setAlignment(Pos.CENTER);

        HBox passwordBox = new HBox();
        //Add label and field
        passwordBox.getChildren().addAll(passwordLabel, passwordField);
        //Positions box in center
        passwordBox.setAlignment(Pos.CENTER);

        // Root is our vertical box, store result at top
        // then the horizontal boxes in the middle with the login button at the bottom
        // Position all elements in center.
        root.getChildren().addAll(resultLabel, usernameBox, passwordBox, loginButton);
        root.setAlignment(Pos.CENTER);

        // Makes the transition to Maximize window seamless.
        // Less irritating on the eyes or maybe theres something wrong with me
        //Dont change this code below before asking
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // Creates window
        primaryStage.setScene(new Scene(root));
        //Maximizes window when we start application
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Macro Builder");
        primaryStage.show();
    }

    // Transition to detail scene after login
    private void showDetailScene(Stage primaryStage) {

        // DUMMY CODE TO SHOW WE ARE IN DETAIL SCENE CAN DELETE
        Label dumbLabel = new Label("I am in Detail Scene!");
        StackPane root = new StackPane();
        root.getChildren().setAll(dumbLabel);
        //Sets new scene to stage keep this to make it easier for you
        Scene detailScene = new Scene(root);
        primaryStage.setScene(detailScene);
    }
}
