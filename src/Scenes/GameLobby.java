package Scenes;

import Components.GameLobbyComponents.AvatarComponents;
import Components.GameLobbyComponents.LiveChatComponents;
import Components.Threads.Music;
import Components.Threads.Timers;
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
/**
 * Class which displays the game lobby where the game takes place
 */
public class GameLobby extends Scenes{

    public static BorderPane bp;

    /**
     * Constructor of the gameLobby class
     * @param WIDTH width of the Scene
     * @param HEIGHT height of the scene
     * @param drawing boolean saying if a user is drawing or not
     */
    public GameLobby(double WIDTH, double HEIGHT, boolean drawing) {
        super(WIDTH, HEIGHT);
        bp = new BorderPane();
        setSc(new Scene(bp, WIDTH, HEIGHT));
        addUIControls(bp, drawing);
    }

    /**
     * Method that creates the game lobby scene with the game-components: Canvas, avatars and livechat
     * @param borderPane creates a borderPane where we put the game-components.
     * @param drawing a boolean checking if the player is drawing. If the player is drawing the drawingcomponents will appear
     */
    private void addUIControls(BorderPane borderPane, boolean drawing){
        HBox canvas = addCanvasUI(drawing);
        VBox livechat = liveChatUI();
        VBox avatar = addAvatarUI();
        borderPane.setCenter(canvas);
        borderPane.setRight(livechat);
        borderPane.setLeft(avatar);
        BorderPane.setMargin(avatar,new Insets(36,2,30,2));
        BorderPane.setMargin(livechat,new Insets(6,2,30,2));
        BorderPane.setMargin(canvas,new Insets(0,2,0,2));
        String url = new File("resources/SquiggleTheme.png").toURI().toString();
        borderPane.setStyle("-fx-background-image: url(\"" + url + "\");");
        if (drawing) {
            borderPane.setBottom(addDrawingUI());
        } else {
            borderPane.setBottom(null);
            BorderPane.setMargin(avatar,new Insets(58,2,30,2));
            BorderPane.setMargin(livechat,new Insets(6,2,52,2));
            BorderPane.setMargin(canvas,new Insets(0,2,0,2));
        }
        setTop();
    }

    /**
     * Sets a HBox with word and timer at the top of the gamelobby-window
     */
    public static void setTop(){
        HBox hb = new HBox();
        hb.setSpacing(60);
        hb.getChildren().addAll(addTimerUI(), addWordUI());
        bp.setTop(hb);
    }
}
