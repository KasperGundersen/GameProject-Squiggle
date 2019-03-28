package Components.GameLobbyComponents;

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
}


