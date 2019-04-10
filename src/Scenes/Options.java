package Scenes;

import Components.Threads.Music;
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
/**
 * Class that create and opens a popup window where you can turn on/off the music
 */
public class Options extends Scenes {
    private GridPane grid;

    /**
     * Constructor of the Options page
     * @param WIDTH width of the scene
     * @param HEIGHT height of the scene
     */
    public Options(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        openOptions();
    }

    /**
     * Method thats creates the popup window
     */
    public void openOptions(){
        Stage window = new Stage();
        window.setTitle("Options");

        grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);

        Label optionsLabel = new Label("Options");
        Css.setHeaderStyle(optionsLabel);
        optionsLabel.setPadding(new Insets(10,10,10,10));
        grid.add(optionsLabel, 0,0);

        Label musicLabel = new Label("Music");
        Css.setStyle(musicLabel);
        grid.add(musicLabel, 0,3);
        CheckBox musicCheckBox = new CheckBox();

        Label partyModeLabel = new Label("Party mode");
        Css.setStyle(partyModeLabel);
        grid.add(partyModeLabel, 0,4);
        CheckBox partyModeBox = new CheckBox();

        if (Music.audio.isPlaying()) {
            musicCheckBox.setSelected(true);
        } else {
            musicCheckBox.setSelected(false);
        }
        grid.add(musicCheckBox, 1,3);
        grid.add(partyModeBox,1,4);
        musicCheckBox.setOnAction(e -> {
            if (musicCheckBox.isSelected()) {
                partyModeBox.setSelected(false);
                Music.stopMusic();
                Music.playMusic(0);
            } else {
                Music.stopMusic();
            }
        });
        partyModeBox.setOnAction(e -> {
            if (partyModeBox.isSelected()) {
                musicCheckBox.setSelected(false);
                Music.stopMusic();
                Music.playMusic(1);
            } else {
                Music.stopMusic();
            }
        });


        Button submitButton = new Button("Submit");
        submitButton.setPrefWidth(100);
        Css.setStyle(submitButton);

        submitButton.setOnAction(e -> {
            window.close();

        });

        grid.add(submitButton, 0,5, 2,1);
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