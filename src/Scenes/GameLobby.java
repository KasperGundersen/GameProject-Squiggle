package Scenes;

import Components.GameLobbyComponents.AvatarComponents;
import Components.GameLobbyComponents.LiveChatComponents;
import Components.Threads.Music;
import Components.Threads.Timers;
import css.Css;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


import java.io.File;

import static Components.GameLobbyComponents.AvatarComponents.addAvatarUI;
import static Components.GameLobbyComponents.CanvasComponents.*;
import static Components.GameLobbyComponents.LiveChatComponents.liveChatUI;
import static Components.GameLobbyComponents.TimerComponent.addTimerUI;
import static Components.GameLobbyComponents.WordComponents.addWordUI;

public class GameLobby extends Scenes{

    public static BorderPane bp;

    public GameLobby(double WIDTH, double HEIGHT, boolean drawing) {
        super(WIDTH, HEIGHT);
        bp = new BorderPane();
        setSc(new Scene(bp, WIDTH, HEIGHT));
        addUIControls(bp, drawing);
    }

    private void addUIControls(BorderPane borderPane, boolean drawing){
        borderPane.setCenter(addCanvasUI(drawing));
        borderPane.setRight(liveChatUI());
        borderPane.setLeft(addAvatarUI());
        String url = new File("resources/SquiggleTheme.png").toURI().toString();
        borderPane.setStyle("-fx-background-image: url(\"" + url + "\");");
        if (drawing) {
            borderPane.setBottom(addDrawingUI());
        } else {
            borderPane.setBottom(null);
        }
        setTop();
    }

    public static void setTop(){
        HBox hb = new HBox();
        hb.setSpacing(60);
        hb.getChildren().addAll(addTimerUI(), addWordUI());
        bp.setTop(hb);
    }

}
