package Components.GameLobbyComponents;

import Components.PointSystem;
import Components.Threads.Timers;
import Components.UserInfo;
import Database.DBConnection;
import Scenes.GameLobby;
import Scenes.MainMenu;
import Scenes.MainScene;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.Timer;
import java.util.concurrent.CountDownLatch;

import static Components.GameLobbyComponents.CanvasComponents.*;
import static Components.Threads.Timers.*;

/**
 * Class that deals with actual game rules and mechanics
 */
public class GameLogicComponents {

    /**
     * Sets canvas according to who is looking at it
     */
    public static void setPrivileges() {
        boolean drawing = UserInfo.getDrawing();
        if (drawing) {
            GameLobby.bp.setBottom(addDrawingUI());
            turnOffTimer();
            timer2();
        } else {
            GameLobby.bp.setBottom(null);
            turnOffTimer2();
            turnOffTimer();
            timer();
        }
    }

    /**
     * Reset method, sets new drawer, clears livechat and updates privileges
     */
    public static void reset() {
        if (!(DBConnection.drawersLeft())) {
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
                                        DBConnection.exitGame();
                                        Timers.setClosed(true);
                                        Timers.turnOffTimer();
                                        Timers.turnOffTimer2();
                                        Timers.turnOffTimer4();
                                        MainScene.gl = null;
                                        MainScene.mm = new MainMenu(1000, 600);
                                        MainScene.setScene(MainScene.mm.getSc());
                                        MainScene.gl = null;
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
                                        if (UserInfo.getDrawing()) {
                                            PointSystem.setPointsDrawer(UserInfo.getUserID());
                                        }
                                        DBConnection.setNewDrawer();
                                        DBConnection.deleteMessages();
                                        UserInfo.setGuessedCorrectly(false);
                                        DBConnection.resetCorrectGuesses();
                                        LiveChatComponents.cleanChat();
                                        UserInfo.setDrawing(DBConnection.isDrawing());
                                        //Update userInfo for drawer();
                                        setPrivileges();
                                        //New canvas
                                        GameLobby.bp.setCenter(CanvasComponents.addCanvasUI());
                                        //New word and timer resets
                                        GameLobby.setTop();
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
    }
}
