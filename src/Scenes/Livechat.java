package Scenes;

import Components.UserInfo;
import Database.DBConnection;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Livechat extends Scenes {
    Timer timer;
    private static GridPane gridPane;
    private static Button backButton;

    public Livechat(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        addUiControls(getGp());
    }

     private void addUiControls(GridPane gridPane) {
        this.gridPane = gridPane;
        gridPane.setAlignment(Pos.TOP_CENTER);

        Label headerLabel = new Label("Livechat");
        headerLabel.setFont(Font.font(15));
        gridPane.add(headerLabel, 1,1,1,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setValignment(headerLabel, VPos.CENTER);


        TextField inputText = new TextField();
        gridPane.add(inputText, 1,2,1,1);
        inputText.setPrefWidth(100);

        Button submitButton = new Button("Submit");
        gridPane.add(submitButton, 1,3,1,1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        submitButton.setPrefWidth(100);
        submitButton.setDefaultButton(true);

        Text chatText = new Text();
        gridPane.add(chatText,1,4,1,1);

        submitButton.setOnAction(e -> {
            String text = inputText.getText();
            DBConnection.insertMessage(text);
            showMessages(chatText, inputText);
            inputText.clear();
        });
        fontChange(UserInfo.getFontSize(), getNodes());
        changeBackground(gridPane, UserInfo.getColor());

         // Go back button
         backButton = new Button("Go Back");
         gridPane.add(backButton, 0, 7);
         GridPane.setHalignment(backButton, HPos.LEFT);
         GridPane.setValignment(backButton, VPos.BOTTOM);

         ///////Button action//////////////////////////////
         backButton.setOnAction(e -> {
             MainScene.mm = new MainMenu(super.getWIDTH(), super.getHEIGHT());
             MainScene.setScene(MainScene.mm.getSc());
         });
    }

    private void showMessages(Text chatText, TextField inputText) {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ArrayList<String> words = DBConnection.getMessages();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < words.size(); i++) {
                    sb.append(words.get(i));
                    sb.append("\n");
                }
                chatText.setText(sb.toString());
                //inputText.clear();
            }
        };
        timer.schedule(task, 0, 5000);
    }

    public static ObservableList<Node> getNodes() {
        return gridPane.getChildren();
    }
}
