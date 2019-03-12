package Scenes;

import Components.GameLobbyComponents.LiveChatComponents;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


import static Components.GameLobbyComponents.CanvasComponents.*;
import static Components.GameLobbyComponents.LiveChatComponents.liveChatUI;

public class GameLobby extends Scenes{

    private BorderPane bp;

    public GameLobby(double WIDTH, double HEIGHT) {
        super(WIDTH, HEIGHT);
        bp = new BorderPane();
        setSc(new Scene(bp, WIDTH, HEIGHT));
        addUIControls(bp);
    }

    private void addUIControls(BorderPane borderPane){
        borderPane.setBottom(addDrawingUI());
        borderPane.setCenter(addCanvasUI());
        borderPane.setRight(liveChatUI());
    }

}
