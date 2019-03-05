package Scenes;

import Components.Authentication;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Tooltip;

import java.io.File;
import java.util.Arrays;

public class SignUp extends Scenes {

    //UI initialiser alt som man bruker som objectvariabler
    private static TextField nameField;
    private static TextField emailField;
    private static PasswordField passwordField;
    private static PasswordField rePasswordField;

    private static Label errorUsernMail;
    private static Label errorPassword;
    private static Label emptyUser;
    private static Label emptyMail;
    private static Label emptyPassword;

    private Button submitButton;
    private Button optionButton;
    private Button backButton;
    private Button leftButton;
    private Button rightButton;

    private ImageView avatarView;

    private static int avatarID = 1;

    public static void visibleUserMail(boolean b){
        errorUsernMail.setVisible(b);
    }
    public static void visiblePassword(boolean b){
        errorPassword.setVisible(b);
    }
    public static void visibleEmptyUser(boolean b){
        emptyUser.setVisible(b);
    }
    public static void visibleEmptyMail(boolean b){
        emptyMail.setVisible(b);
    }
    public static void visibleEmptyPassword(boolean b){
        emptyPassword.setVisible(b);
    }

    //////////////////////////////////////////////////////////////////////////////
    //construktør fra super
    public SignUp(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        //Legg inn metoden som legger til ui
        addUIControls(super.getGp());
    }

    //gettere
    public static String getName(){
        if(nameField.getText().isEmpty()){
            return null;
        }
        return nameField.getText();
    }

    public static String getMail(){
        if(emailField.getText().isEmpty()){
            return null;
        }
        return emailField.getText();
    }

    public static String getPassword(){
        if(passwordField.getText().equals(rePasswordField.getText()) && !passwordField.getText().isEmpty()){
            return passwordField.getText();
        }else {
            return null;
        }
    }

    public static int getAvatarID() {
        return avatarID;
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
        super.errorFont(errorUsernMail);

        // Add Name Label
        Label nameLabel = new Label("Username : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        nameField = new TextField();
        nameField.setPrefHeight(prefHeight);
        nameField.setPromptText("Ola Nordmann");
        gridPane.add(nameField, 1,1);


        //Add empty Label
        emptyUser = new Label("Fill in username");
        gridPane.add(emptyUser,2,1,2,1);
        emptyUser.setVisible(false);
        super.errorFont(emptyUser);

        // Add Email Label
        Label emailLabel = new Label("Email : ");
        gridPane.add(emailLabel, 0, 2);

        // Add Email Text Field
        emailField = new TextField();
        emailField.setPrefHeight(prefHeight);
        emailField.setPromptText("party@myhouse.tonight");
        gridPane.add(emailField, 1, 2);

        //Add empty Label
        emptyMail = new Label("Fill in mail");
        gridPane.add(emptyMail,2,2,2,1);
        emptyMail.setVisible(false);
        errorFont(emptyMail);

        //////////////////////////////////////////

        // Add Name Label
        Label avatarLabel = new Label("Avatar : ");
        gridPane.add(avatarLabel, 0,3);

        avatarView = new ImageView(getAvatar(avatarID));
        avatarView.setFitWidth(150);
        avatarView.setFitHeight(150);
        gridPane.add(avatarView, 1, 3, 1, 1);
        GridPane.setHalignment(avatarView, HPos.CENTER);

        leftButton = new Button("<");
        gridPane.add(leftButton, 1,3);
        GridPane.setHalignment(leftButton, HPos.CENTER);
        GridPane.setMargin(leftButton, new Insets(0,120,0,0));

        rightButton = new Button(">");
        gridPane.add(rightButton, 1,3);
        GridPane.setHalignment(rightButton, HPos.CENTER);
        GridPane.setMargin(rightButton, new Insets(0,0,0,120));

        styleButton(leftButton);
        styleButton(rightButton);

        ///////////////////////////////////////


        //Add error Label
        errorPassword = new Label("Password don't match");
        gridPane.add(errorPassword,1,3,2,1);
        GridPane.setValignment(errorPassword, VPos.BOTTOM);
        errorPassword.setVisible(false);
        super.errorFont(errorPassword);

        // Add Passfword Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 4);

        // Add Password Field
        passwordField = new PasswordField();
        passwordField.setPrefHeight(prefHeight);
        passwordField.setPromptText("password");
        GridPane.setMargin(passwordField, new Insets(10, 0,0,0));
        gridPane.add(passwordField, 1, 4);

        //Add empty Label
        emptyPassword = new Label("Fill in password");
        gridPane.add(emptyPassword,2,4,2,1);
        emptyPassword.setVisible(false);
        errorFont(emptyPassword);

        // Add RePassword Label
        Label rePasswordLabel = new Label("Password : ");
        gridPane.add(rePasswordLabel, 0, 5);

        // Add RePassword Field
        rePasswordField = new PasswordField();
        rePasswordField.setPrefHeight(prefHeight);
        rePasswordField.setPromptText("re-enter password");
        gridPane.add(rePasswordField, 1, 5);

        // Add Submit Button
        submitButton = new Button("Submit");
        submitButton.setPrefHeight(prefHeight);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 6, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setValignment(submitButton, VPos.CENTER);

        // Add option button
        optionButton = new Button("Options");
        gridPane.add(optionButton, 3, 7);
        GridPane.setHalignment(optionButton, HPos.LEFT);
        GridPane.setValignment(optionButton, VPos.BOTTOM);
        // Go back button
        backButton = new Button("Go Back");
        gridPane.add(backButton, 0, 7);
        GridPane.setHalignment(backButton, HPos.LEFT);
        GridPane.setValignment(backButton, VPos.BOTTOM);

        // Tooltips
        final Tooltip tooltipName = new Tooltip();
        tooltipName.setText("Write your username");
        nameField.setTooltip(tooltipName);
        tooltipName.setStyle("-fx-background-color: cornflowerblue;");

        final Tooltip tooltipEmail = new Tooltip();
        tooltipEmail.setText("Write your Email");
        emailField.setTooltip(tooltipEmail);
        tooltipEmail.setStyle("-fx-background-color: cornflowerblue;");

        final Tooltip tooltipPasword = new Tooltip();
        tooltipPasword.setText("Write your password");
        passwordField.setTooltip(tooltipPasword);
        tooltipPasword.setStyle("-fx-background-color: cornflowerblue;");

        final Tooltip tooltipRePassword = new Tooltip();
        tooltipRePassword.setText("Write your password one more time");
        rePasswordField.setTooltip(tooltipRePassword);
        tooltipRePassword.setStyle("-fx-background-color: cornflowerblue;");

        // Button action
        backButton.setOnAction(e -> {
            MainScene.li = new LogIn(super.getWIDTH(), super.getHEIGHT());
            MainScene.setScene(MainScene.li.getSc());
        });
        submitButton.setOnAction(e -> Authentication.submit());
        optionButton.setOnAction(e -> new Options(super.getWIDTH(), super.getHEIGHT()));
        rightButton.setOnAction(e -> loopAvatar(1, 4, 1));
        leftButton.setOnAction(e -> loopAvatar(1,4,-1));

    }

    private Image getAvatar(int i){
        File file = new File("resources\\avatars\\" + i + ".jpg");
        return new Image(file.toURI().toString());
    }

    private void loopAvatar(int min, int max, int add){
        avatarID += add;
        if(avatarID < min){
            avatarID = max;
        }else if(avatarID > max){
            avatarID = min;
        }
        avatarView.setImage(getAvatar(avatarID));
    }

    private void styleButton(Button b){
        b.setPrefHeight(35);
        b.setPrefWidth(25);
        b.setStyle("-fx-background-color: rgba(255, 255, 255 ,0); " +
                "-fx-font-weight: bold ; " +
                "-fx-font-size: 2em; " +
                "-fx-text-fill: white; " +
                "-fx-font-family: ariel;");
    }
}
