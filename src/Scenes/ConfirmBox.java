package Scenes;

import Database.DBConnection;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;

public class ConfirmBox{

    static boolean ansver;
    private static GridPane grid;

    private static final double WIDTH = 350, HEIGHT = 150;

    public static boolean display(String title, String message){
        Stage stage = new Stage();

        grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Label label = new Label();
        label.setText(message);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // lager 2 knapper

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setStyle("-fx-font-size: 15pt;");
        noButton.setStyle("-fx-font-size: 15pt;");

        yesButton.setOnAction(e -> {
            ansver = true;
            stage.close();
            Connection con = DBConnection.getCon();
            DBConnection.setLoggedIn(con, LogIn.getUserName(), 0);
            DBConnection.closeConnection(con);
        });
        noButton.setOnAction(e -> {
            ansver = false;
            stage.close();
        });

        HBox layout = new HBox(10);
        layout.getChildren().addAll(yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 0,0,0));

        grid.add(label, 0,0,2,1);
        grid.add(layout,0,1, 2, 2);


        Scene scene = new Scene(grid);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        return ansver;
    }
}
