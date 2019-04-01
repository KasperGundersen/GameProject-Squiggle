package Scenes;

import Components.GameLobbyComponents.AvatarComponents;
import Components.GameLobbyComponents.GameLogicComponents;
import Components.GameLobbyComponents.LiveChatComponents;
import Components.UserInfo;
import Database.DBConnection;
import Database.HikariCP;
import com.sun.tools.javac.Main;
import css.Css;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class MainMenu extends Scenes{

    public MainMenu(double width, double height) {
        super(width, height);
        addUIControls(getGp());
    }

    private void addUIControls(GridPane gridPane) {
        double prefHeight = 40;
        double prefWidth = 200;
        // Add Header
        Label headerLabel = new Label("Main Menu");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headerLabel.setStyle("-fx-font-size: 40px;");
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Join game button
        Button joinGameButton = new Button("Join Game");
        Css.setStyle(joinGameButton);
        joinGameButton.setPrefHeight(prefHeight);
        joinGameButton.setDefaultButton(true);
        joinGameButton.setPrefWidth(prefWidth);
        gridPane.add(joinGameButton, 0, 1, 2, 1);
        GridPane.setHalignment(joinGameButton, HPos.CENTER);
        GridPane.setValignment(joinGameButton, VPos.CENTER);

        // My Page button
        Button myPageButton = new Button("My page");
        Css.setStyle(myPageButton);
        myPageButton.setPrefHeight(prefHeight);
        myPageButton.setPrefWidth(prefWidth);
        gridPane.add(myPageButton, 0, 2, 2, 1);
        GridPane.setHalignment(myPageButton, HPos.CENTER);
        GridPane.setValignment(myPageButton, VPos.CENTER);

        // Options button
        Button optionButton = new Button("Options");
        Css.setStyle(optionButton);
        optionButton.setPrefHeight(prefHeight);
        optionButton.setPrefWidth(prefWidth);
        gridPane.add(optionButton, 0,3, 2, 1);
        GridPane.setHalignment(optionButton, HPos.CENTER);
        GridPane.setValignment(optionButton, VPos.CENTER);

        // Log Out button
        Button logOutButton = new Button("Log Out");
        Css.setStyle(logOutButton);
        logOutButton.setPrefHeight(prefHeight);
        logOutButton.setPrefWidth(prefWidth);
        gridPane.add(logOutButton, 0,4, 2, 1);
        GridPane.setHalignment(logOutButton, HPos.CENTER);
        GridPane.setValignment(logOutButton, VPos.CENTER);

        // Quit button
        Button quitButton = new Button("Quit");
        Css.setStyle(quitButton);
        quitButton.setPrefHeight(prefHeight);
        quitButton.setPrefWidth(prefWidth);
        gridPane.add(quitButton, 0, 5, 2, 1);
        GridPane.setHalignment(quitButton, HPos.CENTER);
        GridPane.setValignment(quitButton, VPos.CENTER);

        Css.setBackground(gridPane);

        // BUTTON ACTION //////////////
        optionButton.setOnAction(e -> new Options(super.getWIDTH(), super.getHEIGHT()));
        joinGameButton.setOnAction(e -> joinGameSystem());
        logOutButton.setOnAction(e -> {
            if(ConfirmBox.display("Warning!", "Sure you want to log out?")){
                logOutSystem();
            }
        });
        quitButton.setOnAction(e -> {
            MainScene.closeProgram();
        });
        myPageButton.setOnAction(e -> {
            MainScene.mp = new MyPage(super.getWIDTH(), super.getHEIGHT());
            MainScene.setScene(MainScene.mp);
        });
    }

    private void joinGameSystem() {
        DBConnection.joinGame();
        MainScene.gl = new GameLobby(MainScene.getWIDTH(), MainScene.getHEIGHT(), UserInfo.getDrawRound() == GameLogicComponents.getCurrentRound());
        GameLogicComponents.setPrivileges();
        MainScene.setScene(MainScene.gl);
        MainScene.mm = null;
    }

    private void logOutSystem(){
        MainScene.li = new LogIn(super.getWIDTH(), super.getHEIGHT());
        MainScene.setScene(MainScene.li);
        MainScene.mm = null;
        DBConnection.setLoggedIn(UserInfo.getUserName(), 0);
    }
}

