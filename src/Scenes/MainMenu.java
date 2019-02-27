package Scenes;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenu {
    private GridPane gp;
    private Scene sc;

    public MainMenu(double width, double height) {
        gp = new GridPane();
        addUIControls(gp);
        sc = new Scene(gp, width, height);
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
        gridPane.add(joinGameButton, 0,1);

        // Options game button
        Button optionButton = new Button("Options");
        gridPane.add(optionButton, 1,1);

        // Quit button
        Button quitButton = new Button("Quit");
        quitButton.setPrefHeight(prefHeight);
        quitButton.setDefaultButton(true);
        quitButton.setPrefWidth(100);
        gridPane.add(quitButton, 0, 4, 2, 1);
        GridPane.setHalignment(quitButton, HPos.CENTER);
        GridPane.setMargin(quitButton, new Insets(20, 0,20,0));
    }
}

