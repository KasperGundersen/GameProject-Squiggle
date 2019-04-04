package Components.GameLobbyComponents;

import Components.Threads.Timers;
import Components.UserInfo;
import Database.DBConnection;
import Scenes.GameLobby;
import Scenes.MainMenu;
import Scenes.MainScene;
import Scenes.Results;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.concurrent.CountDownLatch;

import static Components.Threads.Timers.*;

/**
 * Class that deals with actual game rules and mechanics
 */
public class GameLogicComponents {

    public static int gameTime = 95;
    private static int currentRound = 1;

    public static int getCurrentRound() {
        return currentRound;
    }

    /**
     * Sets canvas according to who is looking at it
     */
    public static void setPrivileges() {
        Timers.startHeartBeat();
    }

    /**
     * Increments round counter
     */
    public static void incrementRoundCounter() {
        currentRound++;
    }

    /**
     * Sets new drawer, or quits game if everyone has drawn.
     */
    public static void reset() {
        /*
        boolean ok = false;
        while (!ok && currentRound <= DBConnection.getAmtPlayer()) {
            if (DBConnection.playerToDraw(GameLogicComponents.getCurrentRound())) {
                ok = true;
            } else {
                GameLogicComponents.incrementRoundCounter();
            }
        }
        */
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
                                        LiveChatComponents.cleanChat();
                                        GameLogicComponents.setPrivileges();
                                        MainScene.setScene(MainScene.gl);
                                        UserInfo.setGuessedCorrectly(false);
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
            MainScene.rs = new Results(MainScene.getWIDTH(), MainScene.getHEIGHT());
            MainScene.setScene(MainScene.rs);
            MainScene.gl = null;
            DBConnection.exitGame();
            stopHeartBeat();
        }
    }
}