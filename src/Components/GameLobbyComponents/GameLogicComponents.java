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
        if (DBConnection.getDrawing()) {
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
    /*
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
            setClosed(false);
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
                                        if (DBConnection.getDrawing()) {
                                            PointSystem.setPointsDrawer(UserInfo.getUserID());
                                        }
                                        // DBConnection.setNewDrawer();
                                        DBConnection.deleteMessages();
                                        UserInfo.setGuessedCorrectly(false);
                                        DBConnection.resetCorrectGuesses();
                                        LiveChatComponents.cleanChat();
                                        // UserInfo.setDrawing(DBConnection.isDrawing());
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
    */

    public static void newResetMethodThatWorks(){
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {


                return null;
            }
        };
    }
}


