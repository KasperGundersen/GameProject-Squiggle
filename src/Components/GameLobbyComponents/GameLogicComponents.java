package Components.GameLobbyComponents;

import Components.PointSystem;
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
        boolean drawing = UserInfo.getDrawing();
        swapCanvas(drawing);
    }

    /**
     * Swaps between drawable or not drawable canvas
     * @param b Boolean to make drawable or not
     */
    public static void swapCanvas(boolean b) {
        if (b) {
            GameLobby.bp.setBottom(addDrawingUI());
            turnOffTimer();
           timer2(); // might be removed
        } else {
            GameLobby.bp.setBottom(null);
            turnOfTimer2(); // might be removed
            timer();
        }
    }

    /**
     * Method that resets the round, sets new drawer and clears canvas
     */
    public static void reset(){
        PointSystem.setPointsDrawer();
        DBConnection.setNewDrawer();
        DBConnection.deleteMessages();
        UserInfo.setGuessedCorrectly(false);
        DBConnection.resetCorrectGuesses();
        LiveChatComponents.cleanChat();
        UserInfo.setDrawing(DBConnection.isDrawing());


        //Update userInfo for drawer();
        setPrivileges();
        //New canvas
        GameLobby.bp.setCenter(CanvasComponents.addCanvasUI());
        //New word and timer resets
        GameLobby.setTop();
    }
}
