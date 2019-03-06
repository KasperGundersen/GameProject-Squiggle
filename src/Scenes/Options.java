package Scenes;

import Components.UserInfo;
import Scenes.Scenes;
import com.sun.tools.javac.Main;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.scene.control.SpinnerValueFactory.*;


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
       grid.setGridLinesVisible(true);

       Label optionsLabel = new Label("Options");
       optionsLabel.setFont(Font.font("Arial", FontWeight.BOLD,24));
       optionsLabel.setPadding(new Insets(10,10,10,10));
       grid.add(optionsLabel, 0,0);

       Label backgroundColourLabel = new Label("Background colour");
       grid.add(backgroundColourLabel, 0,1);
       ColorPicker cp = new ColorPicker();
       cp.setMinWidth(150);
       grid.add(cp,1,1);

       Label fontSizeLabel = new Label("Font size");
       grid.add(fontSizeLabel, 0,2);
       Spinner fontSizeSpinner = new Spinner();
       int fontSize = UserInfo.getFontSize();
       if (fontSize == 0) {
           fontSize = 16; //Default value
       }

       SpinnerValueFactory<Integer> fontSizeFactory = new IntegerSpinnerValueFactory(10,25,fontSize);
       fontSizeSpinner.setValueFactory(fontSizeFactory);
       grid.add(fontSizeSpinner,1,2);

       Label musicLabel = new Label("Music");
       grid.add(musicLabel, 0,3);
       CheckBox musicCheckBox = new CheckBox();
       grid.add(musicCheckBox, 1,3);


       Button submitButton = new Button("Submit");
       submitButton.setPrefWidth(100);

       submitButton.setOnAction(e -> {

           int fontSizeChoosen = fontSizeFactory.getValue();
           UserInfo.setFontSize(fontSizeChoosen);

           LogIn.fontChange(UserInfo.getFontSize(), LogIn.getNodes());
           MainMenu.fontChange(UserInfo.getFontSize(), MainMenu.getNodes());
           SignUp.fontChange(UserInfo.getFontSize(), SignUp.getNodes());

            window.close();

        });

        grid.add(submitButton, 0,4, 2,1);
       GridPane.setHalignment(submitButton, HPos.CENTER);
       GridPane.setMargin(submitButton, new Insets(20,0,20,0));


        Scene scene = new Scene(grid, 300, 300);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.show();
    }

}