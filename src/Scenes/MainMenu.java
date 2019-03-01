package Scenes;

import Database.DBConnection;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.Connection;

public class MainMenu extends Scenes{
    private GridPane gp;
    private Scene sc;

    public MainMenu(double width, double height) {
        super(width, height);
        addUIControls(super.getGp());
    }

    public Scene getScene() {
        return sc;
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

        // Options button
        Button optionButton = new Button("Options");
        optionButton.setOnAction(e -> Options.openOptions());
        optionButton.setPrefHeight(prefHeight);
        optionButton.setPrefWidth(100);
        gridPane.add(optionButton, 0,2, 2, 1);
        GridPane.setHalignment(optionButton, HPos.CENTER);
        GridPane.setValignment(optionButton, VPos.CENTER);

        // Quit button
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            MainScene.closeStage();
            Connection con = DBConnection.getCon();
            DBConnection.setLoggedIn(con, LogIn.getUserName(), 0);
        });
        quitButton.setPrefHeight(prefHeight);
        quitButton.setPrefWidth(100);
        gridPane.add(quitButton, 0, 3, 2, 1);
        GridPane.setHalignment(quitButton, HPos.CENTER);
        GridPane.setValignment(quitButton, VPos.CENTER);
    }
}

