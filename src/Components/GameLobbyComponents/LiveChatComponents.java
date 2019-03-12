package Components.GameLobbyComponents;

import Database.DBConnection;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LiveChatComponents {
    private static Timer timer = null;
    private static ScrollPane sp;

    //-----------Right-----------//
    public static VBox liveChatUI(){
        VBox vb = new VBox();
        sp = new ScrollPane();
        Text lc = new Text();
        sp.setContent(lc);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        TextField tf = new TextField();
        Button btn = new Button("enter");
        btn.setDefaultButton(true);
        HBox hb = new HBox();
        hb.getChildren().addAll(tf,btn);
        vb.getChildren().addAll(sp,hb);
        vb.setAlignment(Pos.BOTTOM_CENTER);
        showMessages(lc, tf);

        btn.setOnAction(e -> {
            String text = tf.getText();
            DBConnection.insertMessage(text);
            //showMessages(lc, tf);
            tf.clear();
        });

        return vb;
    }

    private static void showMessages(Text chatText, TextField inputText) {
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
                sp.setVvalue(1.0);
            }
        };
        timer.schedule(task, 0, +5000);
    }

    public static void turnOfTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
