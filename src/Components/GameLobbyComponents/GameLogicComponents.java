package Components.GameLobbyComponents;

import Components.UserInfo;
import Database.DBConnection;
import Scenes.GameLobby;

import static Components.GameLobbyComponents.CanvasComponents.*;
import static Components.Threads.Timers.*;

public class GameLogicComponents {

    public static void setPrivileges() {
        boolean drawing = UserInfo.getDrawing();
        swapCanvas(drawing);
    }

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

    public static void reset(){

        DBConnection.setNewDrawer();

        UserInfo.setDrawing(DBConnection.isDrawing());


        //Update userInfo for drawer();
        setPrivileges();
        //New canvas
        GameLobby.bp.setCenter(CanvasComponents.addCanvasUI());
        //New word
        GameLobby.setTop();
        // New chat or maybe just empty the chat???
        // GameLobby.bp.setRight(LiveChatComponents.liveChatUI());
        // new Timer
    }
}
