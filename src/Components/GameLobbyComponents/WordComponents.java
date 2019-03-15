package Components.GameLobbyComponents;

import Components.UserInfo;
import Database.DBConnection;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.awt.*;

public class WordComponents {

    public static HBox addWordUI(){
        HBox hb = new HBox();
        Label word = new Label("Word: " + showWord());
        word.setFont(new Font(20));
        hb.getChildren().add(word);
        return hb;
    }

    private static String showWord(){
        boolean drawing = UserInfo.getDrawing();
        String word = DBConnection.getRandomWord();
        if(drawing){
            return word;
        }else{
            return word.replaceAll("[a-zA-Z]", "_ ");
        }
    }
}
