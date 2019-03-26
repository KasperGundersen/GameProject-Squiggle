package Scenes;

import Components.UserInfo;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

import java.io.File;

import static css.Css.selectorButton;

public abstract class Scenes {

    //Object variables
    private static GridPane gp;
    private Scene sc;
    private static ObservableList<Node> children;

    private final int max = new File("resources/avatars/").listFiles().length - 1;

    // Dimensions
    private final double WIDTH;
    private final double HEIGHT;

    //Constructor for abstract class, use width and height for scene
    public Scenes(double WIDTH, double HEIGHT){
        gp = createGridPane();
        sc = new Scene(gp, WIDTH, HEIGHT);
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    //Getters
    public GridPane getGp() {
        return gp;
    }

    public Scene getSc(){
        return sc;
    }

    public void setSc(Scene sc) {
        this.sc = sc;
    }

    public void setChildren(ObservableList<Node> newValue) {
        this.children = newValue;
    }

    double getHEIGHT() {
        return HEIGHT;
    }

    double getWIDTH() {
        return WIDTH;
    }

    public int getMax() {
        return max;
    }

    //Standard GridPane formation
    private GridPane createGridPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();
        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);
        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        // Set the horizontal gap between columns
        gridPane.setHgap(10);
        // Set the vertical gap between rows
        gridPane.setVgap(10);
        // Add Column Constraints
        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
        return gridPane;
    }

    public static void fontChange(int size, ObservableList<Node> children){
        if(size == 0) {
            size = 12; //default font size
        } else {
            size = UserInfo.getFontSize();
        }

        for (Node child : children) {
            if (child instanceof Button) {
                Button b = (Button) child;
                b.setFont(Font.font("Courier", size));
            }
            if (child instanceof Label) {
                Label l = (Label) child;
                l.setFont(Font.font("Courier", size));
            }
            if (child instanceof Text) {
                Text t = (Text) child;
                t.setFont(Font.font("Courier", size));
            }
        }
    }

    public static void changeBackground(GridPane gridPane, Color color) {
        if (color == null) {
            color = Color.web("0xffe6b3");
        }
        String print = color.toString();
        String formatert = print.replace("0x", "");
        gridPane.setStyle("-fx-background-color:#" + formatert + ";");
    }

    void errorFont(Label l){
        l.setTextFill(Color.RED);
        l.setFont(Font.font(
                "Arial",
                FontPosture.ITALIC,
                Font.getDefault().getSize()
        ));
    }

    void styleSelectorButton(Button b){
        b.setPrefHeight(35);
        b.setPrefWidth(25);
        b.setStyle(selectorButton());
    }

    int loopAvatar(int counter, int add, int min, int max){
        counter += add;
        if(counter < min){
            counter = max;
        }else if(counter > max){
            counter = min;
        }
        return counter;
    }

    public Image getAvatar(int i){
        File file = new File("resources/avatars/" + i + ".jpg");
        return new Image(file.toURI().toString());
    }


}
