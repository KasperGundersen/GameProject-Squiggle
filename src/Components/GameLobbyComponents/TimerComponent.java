package Components.GameLobbyComponents;

import Database.DBConnection;
import Scenes.GameLobby;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import static Components.Threads.Timers.timer4;
import static Components.Threads.Timers.turnOfTimer2;


public class TimerComponent {

    private static Label countDown;
    public static int timeRemaining;

    public static VBox addTimerUI() {
        Date time = DBConnection.getDrawTimer();
        Date currentTime = new Date();
        long diff = time.getTime() - currentTime.getTime();
        timeRemaining = (int) diff / 1000;

        VBox vb = new VBox();
        countDown = new Label("Remaining time: " + timeRemaining);
        countDown.setFont(new Font(20));
        vb.getChildren().add(countDown);
        timer4();
        return vb;
    }

    public static void setTimerText(boolean gameStarted) {
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
                                    timeRemaining--;
                                    if (gameStarted) {
                                        countDown.setText("Remaining time: " + timeRemaining);
                                    } else {
                                        countDown.setText("Game starts in: " + (timeRemaining - 80));
                                    }
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

    public static void setTimeReimaing(int newTime) {
        timeRemaining = newTime;
    }

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
                                    GameLogicComponents.reset();
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
