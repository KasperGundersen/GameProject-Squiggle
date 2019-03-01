package Scenes;

import Database.DBConnection;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;

public class ConfirmBox{

    static boolean ansver;
    private static GridPane grid;

    public static boolean display(String title, String message){
        Stage stage = new Stage();

        grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.setMinHeight(250);

        Label label = new Label();
        label.setText(message);

        // lager 2 knapper

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            ansver = true;
            stage.close();
            Connection con = DBConnection.getCon();
            DBConnection.setLoggedIn(con, LogIn.getUserName(), 0);
        });
        noButton.setOnAction(e -> {
            ansver = false;
            stage.close();
        });

        HBox layout = new HBox(10);
        layout.getChildren().addAll(yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        grid.add(label, 0, 0);
        grid.add(layout,0,2);

        Scene scene = new Scene(grid);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        return ansver;
    }
}
