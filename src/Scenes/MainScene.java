package Scenes;

import Components.Threads.Timers;
import Components.Toast;
import Components.UserInfo;
import Database.DBConnection;
import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;

import java.util.concurrent.CountDownLatch;

public class MainScene {

    private static final double HEIGHT = 600;
    private static final double WIDTH = 1060;

    private static Stage stage;

    // Scenes
    public static Scenes li = new LogIn(WIDTH, HEIGHT);
    public static Scenes mm = null;
    public static Scenes su = null;
    public static Scenes mp = null;
    public static Scenes gl = null;
    public static Scenes lc = null;


    // User
    public static UserInfo user = new UserInfo();
    public static Toast toast = new Toast(stage, WIDTH, HEIGHT);

    public static double getHEIGHT() {
        return HEIGHT;
    }

    public static double getWIDTH() {
        return WIDTH;
    }

    public static void setScene(Scenes sc) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        //Background work
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    stage.setScene(sc.getSc());
                                }finally{
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        //Keep with the background work
                        return null;
                    }
                };
            }
        };
        service.start();

    }

    public void initialize(Stage stage) {
        MainScene.stage = stage;
        MainScene.stage.setTitle("Squiggle");
        MainScene.stage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        MainScene.stage.setResizable(false);
        setScene(li);
        MainScene.stage.show();
    }
    public static void closeProgram(){
        if(ConfirmBox.display("Warning!", "Sure you want to exit?")){
            Components.GameLobbyComponents.LiveChatComponents.turnOffLiveChatTimer();
            DBConnection.setLoggedIn(LogIn.getUserName(), 0);
            DBConnection.exitGame();
            Components.Threads.Timers.stopHeartBeat();
            stage.close();
        }
    }
}
