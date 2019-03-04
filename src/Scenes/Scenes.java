package Scenes;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public abstract class Scenes {

    //Object variables
    private static GridPane gp;
    private Scene sc;

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

    public double getHEIGHT() {
        return HEIGHT;
    }

    public double getWIDTH() {
        return WIDTH;
    }

    //Buttonaction to swap scenes
    public void buttonChangeScene(Button btn, Scenes scn){
        btn.setOnAction(e -> MainScene.setScene(scn.getSc()));
    }

    //Standard GridPane formation
    public GridPane createGridPane() {
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

    public static void fontChange(int size, GridPane grid){
        /*
        Text setFontText = new Text("setFont(\"Courier\", 14)");
        setFontText.setFont(Font.font("Courier", size));
        setFontText

        Text setStyleText = new Text("setStyle(\"-fx-font-family:Courier; -fx-font-size: 14pt\")");
        setStyleText.setStyle("-fx-font-family: Courier; -fx-font-size: 14pt");
        */
        ObservableList<Node> childrenOfScene = grid.getChildren();
        System.out.println(childrenOfScene);

        for (Node child : childrenOfScene) {
            if (child instanceof Button) {
                Button b = (Button) child;
                b.setFont(Font.font("Courier", size));
            }
            if (child instanceof Label) {
                Label l = (Label) child;
                l.setFont(Font.font("Courier", size));
            }
        }
    }

    public void errorFont(Label l){
        l.setTextFill(Color.RED);
        l.setFont(Font.font(
                "Arial",
                FontPosture.ITALIC,
                Font.getDefault().getSize()
        ));
    }
}
