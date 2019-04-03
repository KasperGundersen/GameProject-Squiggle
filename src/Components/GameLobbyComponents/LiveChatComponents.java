package Components.GameLobbyComponents;

import Components.PointSystem;
import Components.UserInfo;
import Database.DBConnection;
import css.Css;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The LiveChatComponents class includes the different methods
 * and JavaFX code in order to implement the livechat.
 *
 * @author maxto
 */

public class LiveChatComponents {
    private static Timer timerLive = null;
    private static ScrollPane sp;
    public static StringBuilder messages = new StringBuilder();
    private static TextField tf;

    //-----------Right-----------//

    /**
     * Implements the layout of the livechat in the gamelobby
     */
    public static VBox liveChatUI(){
        VBox vb = new VBox();
        Label livechatLabel = new Label("Live chat:");
        livechatLabel.setFont(new Font(20));
        livechatLabel.setStyle("-fx-text-fill: white");
        livechatLabel.setPadding(new Insets(0,130, 0, 0));
        //livechatLabel.setAlignment(Pos.TOP_LEFT);
        sp = new ScrollPane();
        sp.setPrefHeight(423);
        sp.setFitToWidth(true);
        Text lc = new Text();
        sp.setContent(lc);

        tf = new TextField();
        Button btn = new Button("enter");
        btn.setDefaultButton(true);
        Css.buttonStyleRed(btn);
        HBox hb = new HBox();
        hb.getChildren().addAll(tf,btn);
        vb.getChildren().addAll(livechatLabel, sp,hb);
        vb.setAlignment(Pos.BOTTOM_CENTER);
        showMessages(lc);

        btn.setOnAction(e -> {
            String text = tf.getText();
            if (UserInfo.getDrawRound() == GameLogicComponents.getCurrentRound()) { //If player is drawing

            }

            if (!(UserInfo.getGuessedCorrectly())) { //If player has not answered correctly yet
                insertMessages(text);
            }
            if (UserInfo.getGuessedCorrectly() && !(checkWord(text))) { //If user wants to write something more but has corrected correct
                insertMessages(text);
            }
        });
        return vb;
    }

    private static void insertMessages(String text) {
        DBConnection.insertMessage(text);
        tf.clear();
    }

    /**
     * Shows the messages written in the chat.
     * Gets the messages from methods in DBConnection
     * @param chatText Text-object which displays the messages
     */
    private static void showMessages(Text chatText) {
        timerLive = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                new Thread(()->{
                    StringBuilder newMessages = DBConnection.getNewMessages();
                    messages.append(newMessages);
                }).start();
                chatText.setText(messages.toString());
                sp.setVvalue(1.0);
            }
        };
        timerLive.schedule(task, 0, +1000);
    }

    /**
     * Turns of the timer when called
     */
    public static void turnOffLiveChatTimer() {
        if (timerLive != null) {
            timerLive.cancel();
            timerLive.purge();
        }
    }

    /**
     * Method that checks if guessed word is correct
     * @param word the word guessed
     * @return true or false depending on the answer
     */
    public static boolean checkWord(String word) {
        boolean correct = false;
        if(!(UserInfo.getDrawRound() == GameLogicComponents.getCurrentRound())) { //Only check if user is guesser
            if (word.equalsIgnoreCase(WordComponents.getWord())) {
                correct = true;
                PointSystem.setPointsGuesser();
                DBConnection.setCorrectGuess(UserInfo.getUserID());
                UserInfo.setGuessedCorrectly(true);
                return correct;
            } else {
                correct = false;
                return correct;
            }
        }
        return correct;
    }


    /**
     * Method that cleans the chat. Used when the game is reset
     */
    public static void cleanChat() {
        messages.setLength(0);
        DBConnection.cleanChat();
    }
}
