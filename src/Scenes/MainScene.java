package Scenes;

import Components.GameLobbyComponents.TimerComponent;
import Components.Threads.Timers;
import Components.Toast;
import Components.UserInfo;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScene {

    private static final double HEIGHT = 600;
    private static final double WIDTH = 1000;

    private static Stage stage;

    // Scenes
    public static Scenes li = new LogIn(WIDTH, HEIGHT);
    public static Scenes mm = null;
    public static Scenes su = null;
    public static Scenes mp = null;
    public static Scenes gl = null;


    // User
    public static UserInfo user = new UserInfo();
    public static Toast toast = new Toast(stage, WIDTH, HEIGHT);


    public static void setScene(Scene sc) {
        stage.setScene(sc);
    }

    public void initialize(Stage stage) {
        MainScene.stage = stage;
        MainScene.stage.setTitle("Squiggle");
        MainScene.stage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        setScene(li.getSc());
        MainScene.stage.show();
    }
    private void closeProgram(){
        if(ConfirmBox.display("Warning!", "Sure you want to exit?")){
            Components.GameLobbyComponents.LiveChatComponents.turnOffLiveChatTimer();
            Timers.setClosed(true);
            Components.Threads.Timers.turnOffTimer();
            Components.Threads.Timers.turnOffTimer2();
            Components.Threads.Timers.turnOffTimer4();
            stage.close();
        }
    }
    static void closeStage() {
        stage.close();
    }
}
