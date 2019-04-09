package Components.Threads;

import Components.GameLobbyComponents.CanvasComponents;
import Components.GameLobbyComponents.GameLogicComponents;
import Components.GameLobbyComponents.WordComponents;
import Components.PointSystem;
import Components.Toast;
import Components.UserInfo;
import Database.DBConnection;
import Scenes.MainScene;
import javafx.concurrent.Service;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import static Components.GameLobbyComponents.AvatarComponents.updateData;
import static Components.GameLobbyComponents.CanvasComponents.makeDrawable;
import static Components.GameLobbyComponents.CanvasComponents.updateImage;
import static Components.GameLobbyComponents.TimerComponent.setTimerText;
import static Components.GameLobbyComponents.TimerComponent.timeRemaining;

public class Timers {

    public static Timer heartBeat;
    private static boolean readyReset = false;
    private static AtomicBoolean start = new AtomicBoolean();
    //private static boolean gameDone = false;
    // private static boolean start;

    public static void startHeartBeat() {
        start.set(true);
        // start = true;
        heartBeat();
    }

    private static void heartBeat(){
        heartBeat = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!start.get()) {
                    return;
                }
                if (timeRemaining == Math.round(GameLogicComponents.gameTime * 0.84)) {
                    makeDrawable(CanvasComponents.getGc());
                }
                if (timeRemaining % 5 == 0) {
                    /*if (!(gameDone)) {
                        if (DBConnection.getAmtCorrect() == DBConnection.getAmtPlayer() -1) {
                            timeRemaining = 5;
                            if (UserInfo.getDrawRound() == GameLogicComponents.getCurrentRound()) {
                                DBConnection.changeTime();
                            }
                            gameDone = true;
                        }
                    }*/
                    updateData();
                }
                if (UserInfo.getDrawRound() == GameLogicComponents.getCurrentRound()) {
                    updateImage();
                } else {
                    CanvasComponents.setImage();
                }
                if (timeRemaining > Math.round(GameLogicComponents.gameTime * 0.84)) {
                    if (!readyReset) {
                        readyReset = true;
                    }
                    setTimerText(false);
                } else if (timeRemaining > 0) {
                    setTimerText(true);
                } else {
                    if (readyReset) {
                        //Her blir amtCorrect av en eller annen grunn resettet
                        if (UserInfo.getDrawRound() == GameLogicComponents.getCurrentRound()) { //If player is drawer
                            PointSystem.setPointsDrawer();
                            DBConnection.resetCorrectGuess(); //Only one player can reset amtOfCorrectGuesses
                        }

                        Toast t = new Toast(MainScene.stage, MainScene.getWIDTH(), MainScene.getHEIGHT());
                        t.makeText(WordComponents.getWord(),2000, 500, 500);
                        GameLogicComponents.incrementRoundCounter();

                        while (GameLogicComponents.getCurrentRound() <= DBConnection.getMaxRound()) {
                            if (DBConnection.playerToDraw(GameLogicComponents.getCurrentRound())) {
                                break;
                            } else {
                                GameLogicComponents.incrementRoundCounter();
                            }
                        }
                        GameLogicComponents.reset();
                        readyReset = false;
                        //gameDone = false;
                    }
                }
            }
        };
        heartBeat.schedule(task, 0, +1000);
        if (!start.get()) {
            task.cancel();
        }
    }

    public static void stopHeartBeat() {
        start.set(false);
        // start = false;
        if (heartBeat != null) {
            heartBeat.cancel();
            heartBeat.purge();
        }
    }
}