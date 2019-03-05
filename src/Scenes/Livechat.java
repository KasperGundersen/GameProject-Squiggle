package Scenes;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Livechat extends Scenes {


    public Livechat(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        addUiControls(getGp());
    }



     private void addUiControls(GridPane grid) {
        int PREFWIDTH = 300;
        grid.setGridLinesVisible(true);

        Label headerLabel = new Label("Livechat");
        headerLabel.setFont(Font.font(15));
        grid.add(headerLabel, 1,1,1,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setValignment(headerLabel, VPos.CENTER);


        TextField inputText = new TextField();
        grid.add(inputText, 1,2,1,1);
        inputText.setPrefWidth(PREFWIDTH);

        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1,3,1,1);
        submitButton.setPrefWidth(PREFWIDTH);
        submitButton.setDefaultButton(true);

        Text chatText = new Text();
        grid.add(chatText,1,4,1,1);


     }

}
