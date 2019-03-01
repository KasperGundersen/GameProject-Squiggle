package Scenes;

import Scenes.Scenes;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import static javafx.scene.control.SpinnerValueFactory.*;


public class Options extends Scenes {
    private static GridPane grid;


    public Options(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        openOptions();
    }

    public static void openOptions(){
        Stage window = new Stage();
        window.setTitle("Options");

       grid = new GridPane();
       grid.setAlignment(Pos.TOP_CENTER);

       Label optionsLabel = new Label("Options");
       optionsLabel.setFont(Font.font("Arial", FontWeight.BOLD,24));
       optionsLabel.setPadding(new Insets(10,10,10,10));
       grid.add(optionsLabel, 0,0);

       Label backgroundColourLabel = new Label("Background colour");
       grid.add(backgroundColourLabel, 0,1);
       ColorPicker cp = new ColorPicker();
       grid.add(cp,1,1);

       Label fontSizeLabel = new Label("Font size");
       grid.add(fontSizeLabel, 0,2);
       Spinner fontSizeSpinner = new Spinner();
       int fontSize;
       SpinnerValueFactory<Integer> fontSizeFactory = new IntegerSpinnerValueFactory(10,25,15);
       fontSizeSpinner.setValueFactory(fontSizeFactory);
       grid.add(fontSizeSpinner,1,2);

       Label musicLabel = new Label("Music");
       grid.add(musicLabel, 0,3);
       CheckBox musicCheckBox = new CheckBox();
       grid.add(musicCheckBox, 1,3);


       Button submitButton = new Button("Submit");
       submitButton.setPrefWidth(100);

       submitButton.setOnAction(e -> {
           Color colour = cp.getValue();
           //String colourString = (String) colour;
           changeBackground(grid, colour);
           System.out.println(colour);

        });

        grid.add(submitButton, 0,4, 2,1);
       GridPane.setHalignment(submitButton, HPos.CENTER);
       GridPane.setMargin(submitButton, new Insets(20,0,20,0));


        Scene scene = new Scene(grid, 300, 300);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.show();
    }

    private static void changeBackground(GridPane grid, Color colour) {
       // String colourChosen = Color.valueOf(colour);
        grid.setStyle("-fx-background-color: " + colour);
    }
}