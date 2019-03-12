package Scenes;

import Components.Authentication;
import Components.UserInfo;
import Database.DBConnection;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.Connection;

import static css.css.toolTip;

public class LogIn extends Scenes {

    // Fields
    private static TextField nameField;
    private static PasswordField passwordField;

    // Error message
    private static Label loginError;

    LogIn(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        addUIControls(getGp());
    }

    public static String getUserName(){
        if(nameField.getText() == null){
            return null;
        }
        return nameField.getText();
    }

    public static String getPassword(){
        if(passwordField.getText() == null){
            return null;
        }
        return passwordField.getText();
    }

    private void addUIControls(GridPane gridPane) {
        double prefHeight = 40;

        // Add Header
        Label headerLabel = new Label("Login");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        //Add error Label
        loginError = new Label();
        gridPane.add(loginError,1,0,2,2);
        loginError.setVisible(false);
        super.errorFont(loginError);

        // Add Name Label
        Label nameLabel = new Label("Username: ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        nameField = new TextField();
        nameField.setPrefHeight(prefHeight);
        nameField.setPromptText("xXPussySlayerXx");
        gridPane.add(nameField, 1,1);

        // Add Password Label
        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        passwordField = new PasswordField();
        passwordField.setPrefHeight(prefHeight);
        passwordField.setPromptText("password");
        gridPane.add(passwordField, 1, 3);

        // Add Login Button
        Button logInButton = new Button("Login");
        logInButton.setStyle("-fx-background-color: #9BFFA0; -fx-border-color: #000000; -fx-border-width: 3px; -fx-font-size: 20px;");

        /*
        logInButton.setStyle("");
        logInButton.setStyle("-fx-text-fill: #ff0009;");
        */

        logInButton.setPrefHeight(prefHeight);
        logInButton.setDefaultButton(true);
        logInButton.setPrefWidth(100);
        gridPane.add(logInButton, 0, 4, 2, 1);
        GridPane.setHalignment(logInButton, HPos.CENTER);
        GridPane.setMargin(logInButton, new Insets(20, 0,20,0));

        // Add Registration Button
        Button regButton = new Button("Register new user");
        regButton.setPrefHeight(prefHeight);
        regButton.setPrefWidth(300);
        gridPane.add(regButton, 0, 5, 2, 1);
        GridPane.setHalignment(regButton, HPos.CENTER);
        GridPane.setMargin(regButton, new Insets(20, 0, 20, 0));

        // Add option button
        Button optionButton = new Button("Options");
        gridPane.add(optionButton, 4, 14);

        // Tooltips
        final Tooltip tooltipName = new Tooltip();
        tooltipName.setText("Write your username");
        nameField.setTooltip(tooltipName);
        tooltipName.setStyle(toolTip());

        final Tooltip tooltipPassword = new Tooltip();
        tooltipPassword.setText("Write your password");
        passwordField.setTooltip(tooltipPassword);
        tooltipPassword.setStyle(toolTip());

        //ButtonAction
        logInButton.setOnAction(e -> loginSystem());
        optionButton.setOnAction(e -> new Options(super.getWIDTH(), super.getHEIGHT()));
        regButton.setOnAction(e -> {
            MainScene.su = new SignUp(super.getWIDTH(), super.getHEIGHT());
            MainScene.setScene(MainScene.su.getSc());
        });
    }

    private static void loginSystem(){
        Authentication.logIn();
        UserInfo.setUserName(getUserName());
        UserInfo.initializeUser(DBConnection.getUserID(getUserName()));
    }

    public static void visibleLoginError(boolean b){
        loginError.setVisible(b);
    }

    public static void setTextLoginError(String newText) {
        loginError.setText(newText);
    }
}