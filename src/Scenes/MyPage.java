package Scenes;

import Components.Authentication;
import Components.UserInfo;
import Database.DBConnection;
import css.Css;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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

public class MyPage extends Scenes{
    private static Button backButton;
    private static Button buttonChoose;
    private static Button buttonLobby;
    private static PasswordField newPassword;
    private static PasswordField repeatPassword;
    private static ImageView chooseAvatar;
    private static Button buttonChangePassword;
    private static String fileLocation = "resources/avatars/";

    //Change by max
    private int avatarID = UserInfo.getAvatarID();

    public MyPage(double WIDTH, double HEIGHT){
        super(WIDTH, HEIGHT);
        addUIControls(getGp());
    }

    private void addUIControls(GridPane gridPane){
        // Header image
        File file = new File("resources/logos/Logo_MyPage.png");
        Image image = new Image(file.toURI().toString());
        ImageView iv = new ImageView(image);
        iv.setFitHeight(180);
        iv.setPreserveRatio(true);

        gridPane.add(iv, 0, 0, 5, 1);
        gridPane.setHalignment(iv, HPos.CENTER);

        // Username label
        Label nameLabel = new Label("Username: " + UserInfo.getUserName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Css.setLabelStyle(nameLabel);
        gridPane.add(nameLabel, 0, 1, 2, 1);
        gridPane.setHalignment(nameLabel, HPos.LEFT);
        gridPane.setValignment(nameLabel, VPos.TOP);

        // Email label
        Label emailLabel = new Label("Email: " + UserInfo.getUserEmail());
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Css.setLabelStyle(emailLabel);
        gridPane.add(emailLabel, 0, 2, 2, 1);
        gridPane.setHalignment(emailLabel, HPos.LEFT);
        gridPane.setValignment(emailLabel, VPos.TOP);

        // Change password Button
        buttonChangePassword = new Button("Change password");
        Css.buttonStyleRed(buttonChangePassword);
        buttonChangePassword.setPrefHeight(40);
        buttonChangePassword.setPrefWidth(180);
        gridPane.add(buttonChangePassword, 0, 3, 2, 1);
        gridPane.setHalignment(buttonChangePassword, HPos.LEFT);

        // Current avatar
        Label currentAvatarLabel = new Label("Current avatar:");
        Css.setLabelStyle(currentAvatarLabel);
        currentAvatarLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(currentAvatarLabel, 0, 4, 2, 1);
        gridPane.setHalignment(currentAvatarLabel, HPos.LEFT);
        Image avatar = getAvatar(avatarID);
        ImageView avatarImage = new ImageView(avatar);
        gridPane.add(avatarImage, 0, 5, 2, 1);
        avatarImage.setFitHeight(150);
        avatarImage.setFitWidth(150);
        gridPane.setHalignment(avatarImage, HPos.LEFT);


        // Avatar selection
        // Select new avatar label
        Label newAvatar = new Label("Select new avatar:");
        Css.setHeaderStyle(newAvatar);
        gridPane.add(newAvatar, 1, 0, 3, 1);
        gridPane.setHalignment(newAvatar, HPos.LEFT);
        gridPane.setMargin(newAvatar, new Insets(100,0,0,495));


        //Add ImageView to show avatar
        ImageView avatarView = new ImageView(getAvatar(avatarID));
        avatarView.setFitWidth(360);
        avatarView.setFitHeight(360);
        gridPane.add(avatarView, 1, 0, 3, 5);
        gridPane.setHalignment(avatarView, HPos.LEFT);
        gridPane.setValignment(avatarView, VPos.TOP);
        gridPane.setMargin(avatarView, new Insets(163, 0, 0, 440));

        //Add button to go left
        Button leftButton = new Button("<");
        gridPane.add(leftButton, 1,2, 3, 5);
        GridPane.setHalignment(leftButton, HPos.LEFT);
        gridPane.setValignment(leftButton, VPos.CENTER);
        GridPane.setMargin(leftButton, new Insets(0,120,0,440));
        super.styleSelectorButton(leftButton);

        //Add button to go right
        Button rightButton = new Button(">");
        gridPane.add(rightButton, 1,2, 3, 5);
        GridPane.setHalignment(rightButton, HPos.LEFT);
        gridPane.setValignment(rightButton, VPos.CENTER);
        GridPane.setMargin(rightButton, new Insets(0,0,0,755));
        super.styleSelectorButton(rightButton);

        // Update current avatar button
        buttonChoose = new Button("Choose avatar");
        Css.buttonStyleRed(buttonChoose);
        buttonChoose.setPrefHeight(40);
        buttonChoose.setPrefWidth(150);
        gridPane.add(buttonChoose, 3, 7, 2, 1);
        gridPane.setHalignment(buttonChoose, HPos.LEFT);
        gridPane.setMargin(buttonChoose, new Insets(0, 0, 0, 320));

        // Back button
        backButton = new Button("Back");
        Css.buttonStyleRed(backButton);
        backButton.setPrefHeight(40);
        backButton.setPrefWidth(80);
        gridPane.add(backButton, 0, 6, 1, 1);
        gridPane.setHalignment(backButton, HPos.LEFT);

        // Button action
        buttonChangePassword.setOnAction(e -> {
            displayNewPassword("Change password");
        });

        Css.setBackground(gridPane);

        buttonChoose.setOnAction(e -> {
            Image chosenAvatar = chosenAvatar(avatarID);
            avatarImage.setImage(chosenAvatar);
            UserInfo.setAvatarID(avatarID);
            DBConnection.setAvatarID(UserInfo.getUserID(), avatarID);
        });


        rightButton.setOnAction(e -> {
            avatarID = super.loopAvatar(avatarID,1, 1,getMax());
            avatarView.setImage(super.getAvatar(avatarID));
        });

        leftButton.setOnAction(e -> {
            avatarID = super.loopAvatar(avatarID, -1,1,getMax());
            avatarView.setImage(super.getAvatar(avatarID));
        });

        backButton.setOnAction(e -> {
            MainScene.setScene(MainScene.mm);
            MainScene.mp = null;
        });
    }

    // Methods that interact with images in resources
    private Image chosenAvatar(int avatarID){
        File file = new File(fileLocation + (avatarID) + ".jpg");
        Image image = new Image(file.toURI().toString());
        return image;
    }

    // ///////////////////NEW PASSWORD POPUP ///////////////////////////////////
    // Method for creating new password - new popup window
    private void displayNewPassword(String title){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label header = new Label("Change password");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Css.setText(header);
        newPassword = new PasswordField();
        newPassword.setPrefHeight(40);
        newPassword.setPrefWidth(200);
        newPassword.setPromptText("Enter new password");

        repeatPassword = new PasswordField();
        repeatPassword.setPrefHeight(40);
        repeatPassword.setPromptText("Repeat your new password");

        Button save = new Button("Save change");
        save.setPrefHeight(40);
        save.setPrefWidth(200);
        Css.buttonStyleRed(save);
        save.setOnAction(e -> {
            if(changePassword()){
                window.close();
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(30);
        grid.setAlignment(Pos.CENTER);
        grid.add(header, 0, 0);
        grid.add(newPassword, 0, 1);
        grid.add(repeatPassword, 0,2);
        grid.add(save, 0, 3);
        grid.setHalignment(save, HPos.CENTER);

        Css.setBackground(grid);
        Scene scene = new Scene(grid, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static boolean changePassword(){
        if(newPassword.getText().equals("") || repeatPassword.getText().equals("")){
            ConfirmBox.displayWarning("Warning", "You have to enter a new password");
            return false;
        }else if(!(newPassword.getText().equals(repeatPassword.getText()))){
            ConfirmBox.displayWarning("Warning", "Your passwords have to be equal");
            return false;
        }else{
            if(Authentication.changePassword(newPassword.getText())){
                ConfirmBox.displayWarning("Success", "Your password was successfully changed!");
            }
        }
        return true;
    }
}
