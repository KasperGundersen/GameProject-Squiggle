package Scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;


public class Options{

    public static void opneBoxer(){
        Stage window = new Stage();
        window.setTitle("Options");


        // Chekboxes
        CheckBox box1 = new CheckBox("Sound");
        CheckBox box2 = new CheckBox("Music");

        Button adsButton = new Button("Remove ads");
        Button ratebutton = new Button("Rate the producer");
        Button restartButton = new Button("Restart user");

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(box1, box2, adsButton, ratebutton, restartButton);

        // save button
        Button saveButton = new Button("Save");
        GridPane.setConstraints(saveButton, 1, 2);



        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.getChildren().addAll(layout, saveButton);

        Scene scene = new Scene(grid, 300, 300);
        window.setScene(scene);
        window.show();
    }
}