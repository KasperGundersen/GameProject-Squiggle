package Scenes;

import css.Css;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class Options extends Scenes {
    private GridPane grid;
    public Options(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        openOptions();
    }

    public void openOptions(){
        Stage window = new Stage();
        window.setTitle("Options");

        grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);

        Label optionsLabel = new Label("Options");
        optionsLabel.setFont(Font.font("Arial", FontWeight.BOLD,24));
        optionsLabel.setPadding(new Insets(10,10,10,10));
        grid.add(optionsLabel, 0,0);

        Label musicLabel = new Label("Music");
        grid.add(musicLabel, 0,3);
        CheckBox musicCheckBox = new CheckBox();
        grid.add(musicCheckBox, 1,3);


        Button submitButton = new Button("Submit");
        submitButton.setPrefWidth(100);
        Css.buttonStyleRed(submitButton);

        submitButton.setOnAction(e -> {
            window.close();

        });

        grid.add(submitButton, 0,4, 2,1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20,0,20,0));

        File file = new File("resources/SquiggleTheme.png");
        Image image = new Image(file.toURI().toString());
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 300, 300);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.show();
    }

}