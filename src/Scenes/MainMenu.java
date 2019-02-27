package Scenes;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class MainMenu {
    private GridPane gp;
    private Scene mainMenu;

    public MainMenu(double width, double height) {
        gp = new GridPane();
        mainMenu = (gp, width, height);
    }


}

