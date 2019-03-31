package Components.GameLobbyComponents;

import Components.Threads.Timers;
import Components.UserInfo;
import Database.DBConnection;
import Scenes.GameLobby;
import Scenes.MainMenu;
import Scenes.MainScene;
import com.mysql.cj.jdbc.admin.MiniAdmin;
import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.WritableImage;

import java.util.concurrent.CountDownLatch;

import static Components.GameLobbyComponents.CanvasComponents.*;
import static Components.Threads.Timers.*;

/**
 * Class that deals with actual game rules and mechanics
 */
public class GameLogicComponents {

    private static int currentRound = 1;

    public static int getCurrentRound() {
        return currentRound;
    }

    /**
     * Sets canvas according to who is looking at it
     */
    public static void setPrivileges() {
        Timers.turnOffTimer4();
        Timers.timer4();
    }

    public static void incrementRoundCounter() {
        currentRound++;
    }

    public static void reset() {
        if (currentRound <= DBConnection.getAmtPlayer()) {
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
                                        MainScene.gl = new GameLobby(MainScene.getWIDTH(), MainScene.getHEIGHT(), UserInfo.getDrawRound() == GameLogicComponents.getCurrentRound());
                                        GameLogicComponents.setPrivileges();
                                        MainScene.setScene(MainScene.gl);
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
        } else {
            turnOffTimer4();
            MainScene.mm = new MainMenu(MainScene.getWIDTH(), MainScene.getHEIGHT());
            MainScene.setScene(MainScene.mm);
            MainScene.gl = null;
        }
    }
}


