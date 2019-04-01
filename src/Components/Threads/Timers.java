package Components.Threads;

import Components.GameLobbyComponents.CanvasComponents;
import Components.GameLobbyComponents.GameLogicComponents;
import Components.UserInfo;
import Database.DBConnection;
import Scenes.GameLobby;
import Scenes.MainScene;
import com.sun.tools.javac.Main;

import java.util.Timer;
import java.util.TimerTask;

import static Components.GameLobbyComponents.AvatarComponents.updateData;
import static Components.GameLobbyComponents.CanvasComponents.updateImage;
import static Components.GameLobbyComponents.TimerComponent.setTimerText;
import static Components.GameLobbyComponents.TimerComponent.timeRemaining;

public class Timers {

    private static Timer timer4;
    private static boolean readyReset = false;

    public static void timer4(){
        timer4 = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining % 5 == 0) {
                    updateData();
                    if (UserInfo.getDrawRound() == GameLogicComponents.getCurrentRound()) {
                        updateImage();
                    } else {
                        CanvasComponents.setImage();
                    }
                }
                if (timeRemaining > 10) {
                    if (!readyReset) {
                        readyReset = true;
                    }
                    setTimerText(false);
                } else if (timeRemaining > 0) {
                    setTimerText(true);
                } else {
                    if (readyReset) {
                        GameLogicComponents.incrementRoundCounter();
                        GameLogicComponents.reset();
                        readyReset = false;
                    }
                }
            }
        };
        timer4.schedule(task, 0, +1000);
    }

    public static void turnOffTimer4() {
        if (timer4 != null) {
            timer4.cancel();
            timer4.purge();
        }
    }
}
