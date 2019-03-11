package Scenes;

import Components.UserInfo;
import Database.DBConnection;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
    private static Button backButton;

    //Change by max
    private static GridPane gridPane;


    public MyPage(double WIDTH, double HEIGHT){
        super(WIDTH, HEIGHT);
        try {
            addUIControls(super.getGp());
        }catch(Exception e){

        }

    }

    private void addUIControls(GridPane gridPane) throws Exception{
        this.gridPane = gridPane;
        Label header = new Label("My Page");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        gridPane.add(header, 1, 0, 1, 1);
        gridPane.setHalignment(header, HPos.LEFT);

        Label nameLabel = new Label("Name: ");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(nameLabel, 0, 1, 1, 1);
        gridPane.setHalignment(nameLabel, HPos.LEFT);

        Label emailLabel = new Label("Email: ");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(emailLabel, 0, 2, 1, 1);
        gridPane.setHalignment(emailLabel, HPos.LEFT);

        Image avatar = getAvatar(1);
        ImageView avatarImage = new ImageView(avatar);
        gridPane.add(avatarImage, 4, 1, 1, 3);
        avatarImage.setFitHeight(150);
        avatarImage.setFitWidth(150);
        gridPane.setHalignment(avatarImage, HPos.CENTER);


        Image[] images = getAllAvatars();
        chooseAvatar = new ImageView(images[index]);
        gridPane.add(chooseAvatar, 1, 3, 1, 3);
        GridPane.setHalignment(chooseAvatar, HPos.CENTER);


        buttonRight = new Button("Go right");
        buttonRight.setOnAction(e -> {
            if (index < 3 && index >= 0) {
                index++;
                chooseAvatar.setImage(images[index]);
            } else {
            }
        });
        gridPane.add(buttonRight, 2, 5, 1, 1);
        gridPane.setHalignment(buttonRight, HPos.LEFT);

        buttonLeft = new Button("Go left");
        buttonLeft.setOnAction(e -> {
            if (index > 0 && index <= 3) {
                index--;
                chooseAvatar.setImage(images[index]);
            } else { }
        });
        gridPane.add(buttonLeft, 0, 5, 1, 1);
        gridPane.setHalignment(buttonLeft, HPos.RIGHT);


        buttonChoose = new Button("Choose avatar");
        buttonChoose.setPrefHeight(40);
        buttonChoose.setPrefWidth(100);
        gridPane.add(buttonChoose, 1, 6, 1, 1);
        gridPane.setHalignment(buttonChoose, HPos.CENTER);



        buttonLobby = new Button("Lobby");
        buttonLobby.setPrefHeight(40);
        buttonLobby.setPrefWidth(50);
        gridPane.add(buttonLobby, 7, 0, 1, 1);
        gridPane.setHalignment(buttonLobby, HPos.CENTER);

        buttonChangePassword = new Button("Change password");
        buttonChangePassword.setPrefHeight(40);
        buttonChangePassword.setPrefWidth(150);
        gridPane.add(buttonChangePassword, 0, 3, 1, 1);
        gridPane.setHalignment(buttonChangePassword, HPos.LEFT);

        // Go back button
        backButton = new Button("Go Back");
        gridPane.add(backButton, 0, 7);
        GridPane.setHalignment(backButton, HPos.LEFT);
        GridPane.setValignment(backButton, VPos.BOTTOM);

        ///////Button action//////////////////////////////
        backButton.setOnAction(e -> {
            MainScene.mm = new MainMenu(super.getWIDTH(), super.getHEIGHT());
            MainScene.setScene(MainScene.mm.getSc());
        });

        buttonChangePassword.setOnAction(e -> {
            displayNewPassword("Change password");
        });

        buttonChoose.setOnAction(e -> {
            DBConnection.setAvatarID(index, UserInfo.getUserID());
            Image chosenAvatar = chosenAvatar(index);
            avatarImage.setImage(chosenAvatar);
        });

        changeBackground(getGrid(), UserInfo.getColor());
    }

    // "fileLocation" is found at the top, and will reference the jpgs no matter the computer.
    public Image getAvatar(int UserID){
        File file =  new File(fileLocation + UserInfo.getAvatarID() + ".jpg");
        Image image = new Image(file.toURI().toString());
        return image;
    }

    private Image chosenAvatar(int index){
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

    public static GridPane getGrid() {
        return gridPane;
    }
}
