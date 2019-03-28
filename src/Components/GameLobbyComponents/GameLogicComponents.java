package Components.GameLobbyComponents;

import Components.UserInfo;
import Database.DBConnection;
import Scenes.GameLobby;
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
        boolean l = DBConnection.getDrawing();
        System.out.println(l);
        System.out.println(UserInfo.getUserName());
        System.out.println("--------");
        if (l) {
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
}


