package Components.GameLobbyComponents;

import Components.UserInfo;
import Database.DBConnection;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.awt.*;

public class WordComponents {

    private static String randomWord;

    public static HBox addWordUI(){
        HBox hb = new HBox();
        showWord();
        Label word = new Label("Word: " + randomWord);
        word.setFont(new Font(28));
        hb.getChildren().add(word);
        return hb;
    }

    public static void showWord(){
        boolean drawing = UserInfo.getDrawing();
        String word = DBConnection.getRandomWord();
        if(drawing){
            randomWord = word;
        }else{
            randomWord = word.replaceAll("[a-zA-Z]", "_ ");
        }
    }
}
