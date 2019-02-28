package Scenes;

import Components.Registration;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SignUp extends Scenes {

    //UI initialiser alt som man bruker som objectvariabler
    private static TextField nameField;
    private static TextField emailField;
    private static PasswordField passwordField;
    private static PasswordField rePasswordField;

    private Button submitButton;
    private Button optionButton;
    private Button backButton;

    //construktør fra super
    public SignUp(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        //Legg inn metoden som legger til ui
        addUIControls(super.getGp());
    }

    //gettere
    public static String getName(){
        if(nameField.getText() == null){
            throw new NullPointerException("Need username");
        }
        return nameField.getText();
    }

    public static String getMail(){
        if(emailField.getText() == null){
            throw new NullPointerException("Need mail");
        }
        return emailField.getText();
    }

    public static String getPassword(){
        if(passwordField.getText() == null || rePasswordField.getText() == null){
            throw new NullPointerException("Need password");
        }
        if(passwordField.getText().equals(rePasswordField.getText())){
            return passwordField.getText();
        }else throw new IllegalArgumentException("Password don't match");
    }

    // Adding UI to Grid
    private void addUIControls(GridPane gridPane) {
        double prefHeight = 40;
        // Add Header
        Label headerLabel = new Label("Sign Up");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Username : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        nameField = new TextField();
        nameField.setPrefHeight(prefHeight);
        nameField.setPromptText("Ola Nordmann");
        gridPane.add(nameField, 1,1);

        // Add Email Label
        Label emailLabel = new Label("Email : ");
        gridPane.add(emailLabel, 0, 2);

        // Add Email Text Field
        emailField = new TextField();
        emailField.setPrefHeight(prefHeight);
        emailField.setPromptText("party@myhouse.tonight");
        gridPane.add(emailField, 1, 2);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        passwordField = new PasswordField();
        passwordField.setPrefHeight(prefHeight);
        passwordField.setPromptText("password");
        gridPane.add(passwordField, 1, 3);

        // Add RePassword Label
        Label rePasswordLabel = new Label("Password : ");
        gridPane.add(rePasswordLabel, 0, 4);

        // Add RePassword Field
        rePasswordField = new PasswordField();
        rePasswordField.setPrefHeight(prefHeight);
        rePasswordField.setPromptText("re-enter password");
        gridPane.add(rePasswordField, 1, 4);

        // Add Submit Button
        submitButton = new Button("Submit");
        submitButton.setPrefHeight(prefHeight);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setValignment(submitButton, VPos.CENTER);

        // Add option button
        optionButton = new Button("Options");
        gridPane.add(optionButton, 5, 14);
        optionButton.setOnAction(e -> Options.openOptions());

        // Go back button
        backButton = new Button("Go Back");
        backButton.setPrefHeight(prefHeight);
        backButton.setDefaultButton(true);
        backButton.setPrefWidth(100);
        gridPane.add(backButton, 0, 5, 1, 2);
        GridPane.setHalignment(backButton, HPos.CENTER);
        GridPane.setValignment(backButton, VPos.CENTER);

        // Button submition
        super.buttonChangeScene(backButton, MainScene.li);
        submitButton.setOnAction(e -> Registration.submit());

    }
}
