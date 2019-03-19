package Components.Threads;

import Components.GameLobbyComponents.CanvasComponents;
import Components.GameLobbyComponents.GameLogicComponents;
import Components.GameLobbyComponents.TimerComponent;
import Scenes.GameLobby;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import static Components.GameLobbyComponents.AvatarComponents.updateData;
import static Components.GameLobbyComponents.TimerComponent.timeRemaining;
import static Components.Threads.TimerLabel.setTimerText;

public class Timers {

    public static Timer timer;
    public static Timer timer2;
    public static Timer timer3;
    public static Timer timer4;

    // Timer that sets the image fetched from DB
    public static void timer(){
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("1");
                CanvasComponents.setImage();
            }
        };
        timer.schedule(task, 0, +5000);
    }

    public static void turnOffTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    // Timer that updates the image on canvas
    public static void timer2(){
        timer2 = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("2");

                UploadThread.updateImage();
            }
        };
        timer2.schedule(task, 0, +5000);
    }

    // Method for turning off timer, used when time has run out
    public static void turnOffTimer2() {
        if (timer2 != null) {
            timer2.cancel();
        }
    }

    public static void timer3(){
        timer3 = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("3");
                updateData();
            }
        };
        timer3.schedule(task, 0, +5000);
    }

    public static void turnOffTimer3() {
        if (timer3 != null) {
            timer3.cancel();
        }
    }

    public static void timer4(){
        timer4 = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("4");
                if (timeRemaining > 80) {
                    setTimerText(false);
                } else if (timeRemaining > 0) {
                    setTimerText(true);
                } else {
                    turnOffTimer2(); // Turns off timer that updates image.
                    turnOffTimer4(); // turns off countdown timer
                    reset();
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

    // Method that resets game, gives new privileges
    private static void reset() {
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
