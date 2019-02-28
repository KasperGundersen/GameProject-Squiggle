package Scenes;

import Components.Registration;
import Database.DBConnection;
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

import java.sql.Connection;

public class SignUp extends Scenes {

    //UI
    private TextField nameField;
    private TextField emailField;
    private PasswordField passwordField;
    private PasswordField rePasswordField;

    private Button submitButton;
    private Button optionButton;
    private Button backButton;


    public SignUp(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        addUIControls(super.getGp());
    }

    public String getName(){
        if(nameField.getText().equals(null)){
            throw new IllegalArgumentException("eow");
        }
        return nameField.getText();
    }

    public String getMail(){
        if(emailField.getText().equals(null)){
            throw new IllegalArgumentException("hilf");
        }
        return emailField.getText();
    }

    public String getPassword(){
        if(passwordField.getText().equals(null) || rePasswordField.getText().equals(null)){
            throw new IllegalArgumentException("HELP");
        }
        if(passwordField.getText().equals(rePasswordField.getText())){
            return passwordField.getText();
        }else throw new IllegalArgumentException("Password don't match");
    }

    private void addUIControls(GridPane gridPane) {

        double prefHeight = 40;
        // Add Header
        Label headerLabel = new Label("Registration Form");
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
        super.buttonAction(backButton, MainScene.li);
        submitButton.setOnAction(e -> submit());

    }

    private void submit(){
        Connection con = DBConnection.getCon();
        String username = getName();
        String mail = getMail();
        String password = getPassword();

        if((DBConnection.alreadyExistsIn(con,"userName", username))||(DBConnection.alreadyExistsIn(con,"userMail", mail))){
            System.out.println("Brukernavn eller epost er allerede registrert");
        }else{
            Registration.registerUser(con, username, password, mail, 0);
        }
        MainScene.setScene2(MainScene.li.getSc());
    }
}
