package Scenes;

import Components.GameLobbyComponents.AvatarComponents;
import Components.GameLobbyComponents.LiveChatComponents;
import css.Css;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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
        HBox canvas = addCanvasUI(drawing);
        VBox livechat = liveChatUI();
        VBox avatar = addAvatarUI();
        borderPane.setCenter(canvas);
        borderPane.setRight(livechat);
        borderPane.setLeft(avatar);
        BorderPane.setMargin(avatar,new Insets(43,0,12,12));
        BorderPane.setMargin(livechat,new Insets(0,0,30,0));
        System.out.println(avatar.getPrefHeight());
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
