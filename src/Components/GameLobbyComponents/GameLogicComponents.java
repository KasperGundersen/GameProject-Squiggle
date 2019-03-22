package Components.GameLobbyComponents;

import Components.UserInfo;
import Database.DBConnection;
import Scenes.GameLobby;
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
        boolean drawing = UserInfo.getDrawing();
        if (drawing) {
            GameLobby.bp.setBottom(addDrawingUI());
            turnOffTimer();
            timer2(); // might be removed
        } else {
            GameLobby.bp.setBottom(null);
            turnOffTimer2(); // might be removed
            timer();
        }
    }

    /**
     * Reset method, sets new drawer, clears livechat and updates privileges
     */
    public static void reset() {
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
                                    DBConnection.setNewDrawer();
                                    DBConnection.deleteMessages();
                                    LiveChatComponents.cleanChat();
                                    UserInfo.setDrawing(DBConnection.isDrawing());

                                    //Update userInfo for drawer();
                                    setPrivileges();
                                    //New canvas
                                    GameLobby.bp.setCenter(CanvasComponents.addCanvasUI());
                                    //New word and timer resets
                                    GameLobby.setTop();
                                    // New chat or maybe just empty the chat???
                                    // GameLobby.bp.setRight(LiveChatComponents.liveChatUI());
                                    // new Timer
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
