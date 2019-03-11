package Scenes;

import Components.UserInfo;
import Database.DBConnection;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;

public class MyPage extends Scenes{
    private static int index = 0;
    private static Button buttonLeft;
    private static Button buttonRight;
    private static Button buttonChoose;
    private static Button buttonLobby;
    private static ImageView chooseAvatar;
    private static Button buttonChangePassword;
    private static String fileLocation = "resources/avatars/";
    private int avatarID = UserInfo.getAvatarID();


    public MyPage(double WIDTH, double HEIGHT){
        super(WIDTH, HEIGHT);
        addUIControls(getGp());
    }

    private void addUIControls(GridPane gridPane){

        // Header label
        Label header = new Label("My Page");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        gridPane.add(header, 0, 0, 10, 1);
        gridPane.setHalignment(header, HPos.LEFT);

        // Name label
        Label nameLabel = new Label("Name: ");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(nameLabel, 0, 1, 1, 1);
        gridPane.setHalignment(nameLabel, HPos.LEFT);

        // Email label
        Label emailLabel = new Label("Email: ");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(emailLabel, 0, 2, 1, 1);
        gridPane.setHalignment(emailLabel, HPos.LEFT);

        // Current avatar
        Image avatar = getAvatar(1);
        ImageView avatarImage = new ImageView(avatar);
        gridPane.add(avatarImage, 4, 1, 1, 3);
        avatarImage.setFitHeight(150);
        avatarImage.setFitWidth(150);
        gridPane.setHalignment(avatarImage, HPos.CENTER);

        // Avatar selection
        //Add ImageView to show avatar
        ImageView avatarView = new ImageView(getAvatar(avatarID));
        avatarView.setFitWidth(150);
        avatarView.setFitHeight(150);
        gridPane.add(avatarView, 1, 3, 1, 1);
        GridPane.setHalignment(avatarView, HPos.CENTER);

        //Add button to go left
        Button leftButton = new Button("<");
        gridPane.add(leftButton, 1,3);
        GridPane.setHalignment(leftButton, HPos.CENTER);
        GridPane.setMargin(leftButton, new Insets(0,120,0,0));
        super.styleSelectorButton(leftButton);

        //Add button to go right
        Button rightButton = new Button(">");
        gridPane.add(rightButton, 1,3);
        GridPane.setHalignment(rightButton, HPos.CENTER);
        GridPane.setMargin(rightButton, new Insets(0,0,0,120));
        super.styleSelectorButton(rightButton);

        // Update curent avatar button
        buttonChoose = new Button("Choose avatar");
        buttonChoose.setPrefHeight(40);
        buttonChoose.setPrefWidth(100);
        gridPane.add(buttonChoose, 1, 6, 1, 1);
        gridPane.setHalignment(buttonChoose, HPos.CENTER);


        // Change password Button
        buttonChangePassword = new Button("Change password");
        buttonChangePassword.setPrefHeight(40);
        buttonChangePassword.setPrefWidth(150);
        gridPane.add(buttonChangePassword, 0, 3, 1, 1);
        gridPane.setHalignment(buttonChangePassword, HPos.LEFT);

        // Button action
        buttonChangePassword.setOnAction(e -> {
            displayNewPassword("Change password"); });
        buttonChoose.setOnAction(e -> {
            DBConnection.setAvatarID(avatarID, UserInfo.getUserID());
            Image chosenAvatar = chosenAvatar(avatarID);
            avatarImage.setImage(chosenAvatar);
        });

        rightButton.setOnAction(e -> {
            avatarID = super.loopAvatar(avatarID,1, 1,4);
            avatarView.setImage(super.getAvatar(avatarID));
        });

        leftButton.setOnAction(e -> {
            avatarID = super.loopAvatar(avatarID, -1,1,4);
            avatarView.setImage(super.getAvatar(avatarID));
        });
    }
    // Methods that interact with images in resources
    public Image getAvatar(int UserID){
        File file =  new File(fileLocation + UserInfo.getAvatarID() + ".jpg");
        Image image = new Image(file.toURI().toString());
        return image;
    }

    private Image chosenAvatar(avatarID){
        File file = new File(fileLocation + (index+1) + ".jpg");
        Image image = new Image(file.toURI().toString());
        return image;
    }

    private Image[] getAllAvatars(){
        Image[] images = new Image[4];
        File file;
        for(int i = 0; i < 4; i++){
            file = new File(fileLocation + (i+1) + ".jpg");
            Image image = new Image(file.toURI().toString());
            images[i] = image;
        }
        return images;
    }

    // Method for creating new password - new popup window
    private void displayNewPassword(String title){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label header = new Label("Change password");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setPrefWidth(200);
        passwordField.setPromptText("Enter new password");

        PasswordField repeatPassword = new PasswordField();
        repeatPassword.setPrefHeight(40);
        repeatPassword.setPromptText("Repeat your new password");

        Button save = new Button("Save change");
        save.setPrefHeight(40);
        save.setPrefWidth(200);
        save.setOnAction(e -> {
            window.close();
        });

        GridPane grid = new GridPane();
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);
        grid.add(header, 0, 0);
        grid.add(passwordField, 0, 1);
        grid.add(repeatPassword, 0,2);
        grid.add(save, 0, 3);
        grid.setHalignment(save, HPos.CENTER);

        Scene scene = new Scene(grid, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
