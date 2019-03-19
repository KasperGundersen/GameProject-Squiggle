package Components.GameLobbyComponents;

import Components.Threads.Timers;
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

import static Components.Threads.Timers.turnOffTimer2;


public class TimerComponent {

    public static Timer timer4;
    public static Label countDown;
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
        Timers.timer4();
        return vb;
    }

    public static void setTimeReimaing(int newTime) {
        timeRemaining = newTime;
    }


}
