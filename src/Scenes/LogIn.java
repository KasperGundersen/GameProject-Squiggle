package Scenes;

import Components.Authentication;
import Components.UserInfo;
import Database.DBConnection;
import css.Css;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;

import static css.Css.toolTip;

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

        File file = new File("resources/Logo.png");
        Image image = new Image(file.toURI().toString());
        ImageView iv = new ImageView(image);
        gridPane.add(iv,0,0,2,1);

        // Add Header
        //Label headerLabel = new Label("Login");
        //headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        //Css.setHeaderStyle(headerLabel);
        //gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(iv, HPos.CENTER);
        GridPane.setMargin(iv, new Insets(20, 0,20,0));

        //Add error Label
        loginError = new Label();
        Css.setText(loginError);
        gridPane.add(loginError,1,0,2,2);
        loginError.setVisible(false);
        super.errorFont(loginError);

        // Add Name Label
        Label nameLabel = new Label("Username: ");
        Css.setLabelStyle(nameLabel);
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        nameField = new TextField();
        Css.setStyle(nameField);
        nameField.setPrefHeight(prefHeight);
        nameField.setPrefWidth(200);
        nameField.setPromptText("xXPussySlayerXx");
        gridPane.add(nameField, 1,1);

        // Add Password Label
        Label passwordLabel = new Label("Password: ");
        Css.setLabelStyle(passwordLabel);
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        passwordField = new PasswordField();
        Css.setStyle(passwordField);
        passwordField.setPrefHeight(prefHeight);
        passwordField.setPrefWidth(100);
        passwordField.setPromptText("password");
        gridPane.add(passwordField, 1, 3);

        // Add Login Button
        Button logInButton = new Button("Login");
        Css.buttonStyleRed(logInButton);
        logInButton.setPrefHeight(prefHeight);
        logInButton.setDefaultButton(true);
        logInButton.setPrefWidth(100);
        gridPane.add(logInButton, 0, 4, 2, 1);
        GridPane.setHalignment(logInButton, HPos.CENTER);
        GridPane.setMargin(logInButton, new Insets(20, 0,20,0));

        // Add Registration Button
        Button regButton = new Button("Register new user");
        Css.buttonStyleRed(regButton);
        regButton.setPrefHeight(prefHeight);
        regButton.setPrefWidth(300);
        gridPane.add(regButton, 0, 5, 2, 1);
        GridPane.setHalignment(regButton, HPos.CENTER);
        GridPane.setMargin(regButton, new Insets(20, 0, 20, 0));



        // Help button
        Button helpbutton = new Button("Help");
        Css.buttonStyleRed(helpbutton);
        helpbutton.setPrefWidth(80);
        gridPane.add(helpbutton, 0, 14);

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
       // optionButton.setOnAction(e -> new Options(super.getWIDTH(), super.getHEIGHT()));
        regButton.setOnAction(e -> {
            MainScene.su = new SignUp(super.getWIDTH(), super.getHEIGHT());
            MainScene.setScene(MainScene.su);
            MainScene.li = null;
        });
        helpbutton.setOnAction(e -> new Help(super.getWIDTH(), super.getHEIGHT()));


        Css.setBackground(gridPane);
        //changeBackground(getGrid(), UserInfo.getColor());
    }

    private void loginSystem(){
        Authentication.logIn(getWIDTH(), getHEIGHT());
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