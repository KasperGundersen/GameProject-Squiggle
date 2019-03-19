package Components.GameLobbyComponents;

import Components.Threads.Timers;
import Components.UserInfo;
import Scenes.GameLobby;

import static Components.GameLobbyComponents.CanvasComponents.*;

public class GameLogicComponents {

    public static void setPrivileges() {
        boolean drawing = UserInfo.getDrawing();
        swapCanvas(drawing);
    }

    public static void swapCanvas(boolean b) {
        if (b) {
            GameLobby.bp.setBottom(addDrawingUI());
            Timers.turnOffTimer();
            Timers.timer2(); // might be removed
        } else {
            GameLobby.bp.setBottom(null);
            Timers.turnOffTimer2(); // might be removed
            Timers.timer();
        }
    }

    public static void reset(){
        //DBConnection.setNewDrawer();
        //Update userInfo for drawer();
        setPrivileges();
        //New canvas
        GameLobby.bp.setCenter(CanvasComponents.addCanvasUI());
        //New word
        GameLobby.setTop();
        //New chat
        GameLobby.bp.setRight(LiveChatComponents.liveChatUI());
        //new Timer
    }
}
