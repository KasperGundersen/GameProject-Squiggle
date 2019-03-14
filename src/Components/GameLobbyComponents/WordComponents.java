package Components.GameLobbyComponents;

import Components.UserInfo;
import Database.DBConnection;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

import java.awt.*;

public class WordComponents {

    private static String randomWord;

    public static HBox addWordUI(){
        HBox hb = new HBox();
        showWord();
        Label word = new Label("Word: " + randomWord);
        hb.getChildren().add(word);
        return hb;
    }

    public static void showWord(){
        boolean drawing = UserInfo.getDrawing();
        String word = DBConnection.getRandomWord();
        if(drawing){
            randomWord = word;
        }else{
            randomWord = word.replaceAll("[a-zA-Z]", "_");
        }
    }
}
