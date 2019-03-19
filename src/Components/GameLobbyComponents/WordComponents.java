package Components.GameLobbyComponents;

import Components.UserInfo;
import Database.DBConnection;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.awt.*;

public class WordComponents {
    private static String word = generateWord();

    public static HBox addWordUI(){
        HBox hb = new HBox();
        Label word = new Label("Word: " + showWord());
        word.setFont(new Font(20));
        hb.getChildren().add(word);
        return hb;
    }

    public static String getWord(){
        return word;
    }

    public static String generateWord(){
        return DBConnection.getRandomWord();
    }

    public static String showWord(){
        String line = "___";
        String space = "   ";
        String result ="";
        boolean drawing = UserInfo.getDrawing();
        String word = getWord();
        if(drawing){
            return word;
        }else{
            int letters = word.length();
            for(int i = 0; i < word.length(); i++){
                char letter = word.charAt(i);
                if(letter != ' '){
                    result += line;
                }else{
                    result += space;
                }

            }
            return result;
           // return word.replaceAll("[a-zA-Z]", "_ ");
        }
    }
}
