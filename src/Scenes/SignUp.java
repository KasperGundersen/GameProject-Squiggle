package Scenes;

import Components.Authentication;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class SignUp extends Scenes {

    //UI initialiser alt som man bruker som objectvariabler
    private static TextField nameField;
    private static TextField emailField;
    private static PasswordField passwordField;
    private static PasswordField rePasswordField;

    private static Label errorUsernMail;
    private static Label errorPassword;

    private Button submitButton;
    private Button optionButton;
    private Button backButton;

    public static void visiableUserMail(boolean b){
        errorUsernMail.setVisible(b);
    }
    public static void visiablePassword(boolean b){
        errorPassword.setVisible(b);
    }

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

        //Add error Label
        errorUsernMail = new Label("Username or email already taken");
        gridPane.add(errorUsernMail,1,0,2,2);
        errorUsernMail.setVisible(false);
        errorUsernMail.setTextFill(Color.RED);
        errorUsernMail.setFont(Font.font(
                "Arial",
                FontPosture.ITALIC,
                Font.getDefault().getSize()
        ));

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

        //Add error Label
        errorPassword = new Label("Password don't match");
        gridPane.add(errorPassword,1,2,2,2);
        GridPane.setMargin(headerLabel, new Insets(10, 0,10,0));
        errorPassword.setVisible(false);
        errorPassword.setTextFill(Color.RED);
        errorPassword.setFont(Font.font(
                "Arial",
                FontPosture.ITALIC,
                Font.getDefault().getSize()
        ));

        // Add Passfword Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        passwordField = new PasswordField();
        passwordField.setPrefHeight(prefHeight);
        passwordField.setPromptText("password");
        GridPane.setMargin(passwordField, new Insets(10, 0,0,0));
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
        gridPane.add(optionButton, 3, 6);
        GridPane.setHalignment(optionButton, HPos.LEFT);
        GridPane.setValignment(optionButton, VPos.BOTTOM);
        // Go back button
        backButton = new Button("Go Back");
        gridPane.add(backButton, 0, 6);
        GridPane.setHalignment(backButton, HPos.LEFT);
        GridPane.setValignment(backButton, VPos.BOTTOM);

        // Button submition
        super.buttonChangeScene(backButton, MainScene.li);
        submitButton.setOnAction(e -> Authentication.submit());
        optionButton.setOnAction(e -> Options.openOptions());
    }
}
