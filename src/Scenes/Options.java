package Scenes;

import Scenes.Scenes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;


public class Options extends Scenes {


    public Options(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        openOptions();
    }

    public static void openOptions(){
        Stage window = new Stage();
        window.setTitle("Options");

       GridPane grid = new GridPane();
       grid.setAlignment(Pos.TOP_CENTER);

       Label optionsLabel = new Label("Options");
       optionsLabel.setFont(Font.font("Arial", FontWeight.BOLD,24));
       optionsLabel.setPadding(new Insets(10,10,10,10));
       grid.add(optionsLabel, 0,0);

       Label backgroundColourLabel = new Label("Background colour");





        Scene scene = new Scene(grid, 300, 300);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.show();
    }
}