package css;

import javafx.scene.control.Button;

public class Css {
    public static String selectorButton(){
        return "-fx-background-color: rgba(255, 255, 255 ,0); " +
                "-fx-font-weight: bold ; " +
                "-fx-font-size: 2em; " +
                "-fx-text-fill: white; " +
                "-fx-font-family: ariel;";
    }

    public static String toolTip(){
        return "-fx-background-color: cornflowerblue;";
    }

    public static String confirmButton(){
        return "-fx-font-size: 15pt;";
    }

    public static void setStyle(Button button) {
        button.setStyle("-fx-background-color: #ff94a9;" +
                "-fx-border-color: #ffda94; -fx-border-width: 3px;");
    }
}
