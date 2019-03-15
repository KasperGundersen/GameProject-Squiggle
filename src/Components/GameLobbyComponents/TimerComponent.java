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


public class TimerComponent {

    public static Timer timer4;
    private static Label countDown;
    private static int timeRemaining;

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

    public static void timer4(){
        timer4 = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 80) {
                    setTimerText(false);
                } else if (timeRemaining > 0) {
                    setTimerText(true);
                } else {
                    CanvasComponents.turnOfTimer2(); // Turns off timer that updates image.
                    GameLogicComponents.reset();
                    turnOffTimer4(); // turns off countdown timer
                }
            }
        };
        timer4.schedule(task, 0, +1000);
    }

    public static void turnOffTimer4() {
        if (timer4 != null) {
            timer4.cancel();
        }
    }

    private static void setTimerText(boolean gameStarted) {
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
}
