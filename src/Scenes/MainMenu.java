package Scenes;

import Components.UserInfo;
import Database.DBConnection;
import com.sun.tools.javac.Main;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.Connection;


class MainMenu extends Scenes{
    MainMenu(double width, double height) {
        super(width, height);
        addUIControls(getGp());
    }

    private void addUIControls(GridPane gridPane) {
        double prefHeight = 40;
        // Add Header
        Label headerLabel = new Label("Main Menu");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Join game button
        Button joinGameButton = new Button("Join Game");
        joinGameButton.setPrefHeight(prefHeight);
        joinGameButton.setDefaultButton(true);
        joinGameButton.setPrefWidth(100);
        gridPane.add(joinGameButton, 0, 1, 2, 1);
        GridPane.setHalignment(joinGameButton, HPos.CENTER);
        GridPane.setValignment(joinGameButton, VPos.CENTER);

        // My Page button
        Button myPageButton = new Button("My page");
        myPageButton.setPrefHeight(prefHeight);
        myPageButton.setPrefWidth(100);
        gridPane.add(myPageButton, 0, 2, 2, 1);
        GridPane.setHalignment(myPageButton, HPos.CENTER);
        GridPane.setValignment(myPageButton, VPos.CENTER);

        // Options button
        Button optionButton = new Button("Options");
        optionButton.setPrefHeight(prefHeight);
        optionButton.setPrefWidth(100);
        gridPane.add(optionButton, 0,3, 2, 1);
        GridPane.setHalignment(optionButton, HPos.CENTER);
        GridPane.setValignment(optionButton, VPos.CENTER);

        // Log Out button
        Button logOutButton = new Button("Log Out");
        logOutButton.setPrefHeight(prefHeight);
        logOutButton.setPrefWidth(100);
        gridPane.add(logOutButton, 0,4, 2, 1);
        GridPane.setHalignment(logOutButton, HPos.CENTER);
        GridPane.setValignment(logOutButton, VPos.CENTER);

        // Quit button
        Button quitButton = new Button("Quit");
        quitButton.setPrefHeight(prefHeight);
        quitButton.setPrefWidth(100);
        gridPane.add(quitButton, 0, 5, 2, 1);
        GridPane.setHalignment(quitButton, HPos.CENTER);
        GridPane.setValignment(quitButton, VPos.CENTER);

        // Livechat button
        Button livechatButton = new Button("Live chat");
        livechatButton.setPrefHeight(prefHeight);
        livechatButton.setPrefWidth(100);
        gridPane.add(livechatButton, 0, 6, 2, 1);
        GridPane.setHalignment(livechatButton, HPos.CENTER);
        GridPane.setValignment(livechatButton, VPos.CENTER);

        //Button action
        optionButton.setOnAction(e -> new Options(super.getWIDTH(), super.getHEIGHT()));
        joinGameButton.setOnAction(e -> joinGameSystem());
        logOutButton.setOnAction(e -> logOutSystem());
        quitButton.setOnAction(e -> {
            if (ConfirmBox.display("Do you want to quit?", "Sure you want to exit?")){
                MainScene.closeStage();
            }
        });
        myPageButton.setOnAction(e -> {
            MainScene.mp = new MyPage(super.getWIDTH(), super.getHEIGHT());
            MainScene.setScene(MainScene.mp.getSc());
        });
        livechatButton.setOnAction(e -> {
            MainScene.lc = new Livechat(super.getWIDTH(), super.getHEIGHT());
            MainScene.setScene(MainScene.lc.getSc());
        });
    }

    private void joinGameSystem(){
        DBConnection.enterGame();
        DBConnection.setDrawer();
        MainScene.setScene(MainScene.sq.getSc());
    }

    private void logOutSystem(){
        MainScene.li = new LogIn(super.getWIDTH(), super.getHEIGHT());
        MainScene.setScene(MainScene.li.getSc());
        DBConnection.setLoggedIn(UserInfo.getUserName(), 0);
    }
}

