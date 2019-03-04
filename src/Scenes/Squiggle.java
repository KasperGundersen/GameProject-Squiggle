package Scenes;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Squiggle extends Scenes{

    public Squiggle(double width, double height) {
        super(width, height);
        addUIControls(super.getGp());
    }



    private void addUIControls(GridPane gridPane){
        //Buttons
        ToggleButton draw = new ToggleButton("Draw");
        ToggleButton erase = new ToggleButton("Erase");

        ToggleButton[] tools = {draw, erase};

        ToggleGroup tg = new ToggleGroup();

        for(ToggleButton tool : tools){
            tool.setMinWidth(50);
            tool.setToggleGroup(tg);
            tool.setCursor(Cursor.HAND);
        }

        VBox btns = new VBox(10);
        btns.getChildren().addAll(draw, erase);
        btns.setPadding(new Insets(5));
        btns.setStyle("-fx-background-color: #999");
        btns.setPrefWidth(60);

        gridPane.add(btns, 0,0);

        Canvas canvas = new Canvas(800, 580);
        canvas.setStyle("-fx-border-style: solid inside;" +"-fx-border-color: black;" + "-fx-border-width: 2;" + "-fx-border-radius: 5;" +
                "-fx-border-insets: 5;" +"-fx-border-padding: 10;");
        gridPane.add(canvas, 1,1,5,5);

        ColorPicker cp = new ColorPicker(Color.BLACK);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);

        canvas.setOnMousePressed(e-> {
            if (draw.isSelected()) {
                gc.setStroke(cp.getValue());
                gc.beginPath();
                gc.lineTo(e.getX(), e.getY());

            }else if(erase.isSelected()){
                gc.clearRect(e.getX() - 1, e.getY() - 1, 20, 20);
            }
        });

        canvas.setOnMouseDragged(e-> {
            if (draw.isSelected()) {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();

            }else if(erase.isSelected()){
                gc.clearRect(e.getX() - 1, e.getY() -1 , 20,20);
            }
        });

        canvas.setOnMouseReleased(e-> {
            if(draw.isSelected()){
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
                gc.closePath();
            }else if(erase.isSelected()){
                gc.clearRect(e.getX() - 1, e.getY() - 1, 20, 20);
            }
        });
    }
}
