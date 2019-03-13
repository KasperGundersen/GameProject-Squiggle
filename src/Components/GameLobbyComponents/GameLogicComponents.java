package Components.GameLobbyComponents;

import Components.UserInfo;

public class GameLogicComponents {
    public static void setPrivileges() {
        boolean drawing = UserInfo.getDrawing();
        CanvasComponents.swapCanvas(drawing);
    }
}
