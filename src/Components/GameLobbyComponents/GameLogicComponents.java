package Components.GameLobbyComponents;

import Components.UserInfo;
import Scenes.GameLobby;

import static Components.GameLobbyComponents.CanvasComponents.*;

public class GameLogicComponents {
    public static void setPrivileges() {
        boolean drawing = UserInfo.getDrawing();
        swapCanvas(drawing);
    }

    public static void swapCanvas(boolean b){
        if (b){
            GameLobby.bp.setBottom(addDrawingUI());
            CanvasComponents.turnOfTimer();
        } else{
            GameLobby.bp.setBottom(null);
            CanvasComponents.timer();
        }
    }
}
