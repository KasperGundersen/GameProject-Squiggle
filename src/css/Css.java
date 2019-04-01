package css;


import javafx.geometry.Pos;
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
        button.setAlignment(Pos.BASELINE_CENTER);
        button.setStyle("-fx-background-color: #ff94a9;" +
                "-fx-border-color: #ffda94; -fx-border-width: 3px;" +
                "-fx-pref-width: 300px;"
                );
    }

    public static void setStyle(TextField field) {
        field.setStyle("-fx-background-color: rgb(255,255,255);" +
                "-fx-border-color: #9a4a7c;" +
                "-fx-border-width: 3px;");
    }

    public static void setHeaderStyle(Label label) {
        label.setStyle("-fx-text-fill: white;"+
                "fx-stroke: black;" +
                "fx-stroke-width: 1px;" +
                "-fx-font-size: 40");
    }
    public static void setLabelStyle(Label label) {
        label.setStyle("-fx-text-fill: white;" +
                "-fx-font-size: 14;"
                );
    }

    public static void setBackground(GridPane grid){
        String url = new File("resources/SquiggleTheme.png").toURI().toString();
        grid.setStyle("-fx-background-image: url(" + url + ");");
    }

    public static void setText(Label label){
        label.setStyle("-fx-text-fill: #fefff3; -fx-font-size: 17px; -fx-font-weight: BOLD");
    }

    public static void buttonStyle(Button button){
        button.setStyle("\n" +
                "    -fx-background-color: \n" +
                "        #9a4a7c,\n" +
                "        linear-gradient(#f81de9 0%, #d444ee 20%, #9b119d 100%),\n" +
                "        linear-gradient(#d444ee, #9b119d),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 5,4,3,5;\n" +
                "    -fx-background-insets: 0,1,2,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 10 20 10 20;");
    }

    public static void buttonStyleRed(Button button){
        button.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #9a4a7c 0%, #9a4a7c 100%),\n" +
                "        #9b119d,\n" +
                "        #d86e3a,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #f81de9, #c90dee);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 2,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");
    }
}
