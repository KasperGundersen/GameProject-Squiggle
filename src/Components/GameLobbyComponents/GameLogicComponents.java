package Components.GameLobbyComponents;

import Components.UserInfo;

import static Components.GameLobbyComponents.CanvasComponents.*;

public class GameLogicComponents {
    public static void setPrivileges() {
        boolean drawing = UserInfo.getDrawing();
        CanvasComponents.swap(drawing);
    }

    public static void swapCanvas(boolean b){
        if (b){
            canvas.toFront();
        } else{
            imv.toFront();
        }
        canvas.setVisible(b);
        imv.setVisible(!b);
    }
}
