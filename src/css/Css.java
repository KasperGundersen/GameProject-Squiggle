package css;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.File;

public class Css {
    public static String selectorButton(){
        return "-fx-background-color: rgba(255, 255, 255 ,0); " +
                "-fx-font-weight: bold ; " +
                "-fx-font-size: 7em; " +
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
                "-fx-border-color: #9a4a7c; -fx-border-width: 3px;");
    }

    public static void setStyle(TextField field) {
        field.setStyle("-fx-background-color: rgb(255,255,255);" +
                "-fx-border-color: #9a4a7c;" +
                "-fx-border-width: 3px;");

    }

    public static void setBackground(GridPane grid){
        String url = new File("resources/SquiggleTheme.png").toURI().toString();
        grid.setStyle("-fx-background-image: url(\"" + url + "\");");
    }

    public static void setText(Label label){
        label.setStyle("-fx-text-fill: #fefff3; -fx-font-size: 17px; -fx-font-weight: BOLD");
    }
}
