package Scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;


public class Squiggle extends Scenes{

    private BorderPane bp;

    public Squiggle(double width, double height) {
        super(width, height);
        bp = new BorderPane();
        //setSc(new Scene(bp, width, height));
        addUIControls(getGp());
    }
    private double eraserSize;

    private void addUIControls(GridPane gridPane){

        File pencilFile = new File("resources/icons/pencil.png");
        File rubberFile = new File("resources/icons/rubber.png");
        Image pencil = new Image(pencilFile.toURI().toString());
        Image rubber = new Image(rubberFile.toURI().toString());
        gridPane.setGridLinesVisible(true);

        /*-----------Adding draw and erase buttons------*/
        ToggleButton draw = new ToggleButton("Draw");
        ToggleButton erase = new ToggleButton("Erase");

        ToggleButton[] tools = {draw, erase};

        ToggleGroup tg = new ToggleGroup();

        for(ToggleButton tool : tools){
            tool.setMinWidth(50);
            tool.setToggleGroup(tg);
            tool.setCursor(Cursor.HAND);
        }

        /*------Adding buttons to change linewidth--------*/
        ToggleButton lineWidt1 = new ToggleButton("1");
        ToggleButton lineWidth2 = new ToggleButton("2");
        ToggleButton lineWidth3 = new ToggleButton("3");
        ToggleButton lineWidth4 = new ToggleButton("4");

        ToggleButton[] penSize = {lineWidt1, lineWidth2, lineWidth3, lineWidth4};

        ToggleGroup lineWidthtg = new ToggleGroup();

        for(ToggleButton tb : penSize){
            tb.setMinWidth(50);
            tb.setToggleGroup(lineWidthtg);
            tb.setCursor(Cursor.HAND);
        }

        /*-------------Drawing CanvasComponents-------------*/
        Canvas canvas = new Canvas(800, 580);
        gridPane.add(canvas, 1,1,5,5);

        ColorPicker cp = new ColorPicker();
        cp.setValue(Color.BLACK);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);

        /*Adding what happens when a color is selected in the color palette*/
        cp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cp.setValue(cp.getValue());
            }
        });

        /*---------Grouping buttons together in a box----------*/
        VBox buttons = new VBox(10);
        buttons.getChildren().addAll(draw, erase, cp, lineWidt1, lineWidth2, lineWidth3, lineWidth4);
        buttons.setPadding(new Insets(5));
        buttons.setStyle("-fx-background-color: #999");
        buttons.setPrefWidth(60);

        gridPane.add(buttons, 0,0);

        /*-----------Drawing Logic------------*/

        eraserSize = 5;

        canvas.setOnMousePressed(e-> {
            if (draw.isSelected()) {
                gc.setStroke(cp.getValue());
                gc.beginPath();
                gc.lineTo(e.getX(), e.getY());

            }else if(erase.isSelected()){
                gc.clearRect(e.getX() - 1, e.getY() - 1, eraserSize, eraserSize);
            }
        });

        canvas.setOnMouseDragged(e-> {
            if (draw.isSelected()) {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();

            }else if(erase.isSelected()){
                gc.clearRect(e.getX() - 1, e.getY() -1 , eraserSize,eraserSize);
            }
        });

        canvas.setOnMouseReleased(e-> {
            if(draw.isSelected()){
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
                gc.closePath();
            }else if(erase.isSelected()){
                gc.clearRect(e.getX() - 1, e.getY() - 1, eraserSize, eraserSize);
            }
        });

        /*---------Changing Cursors------------*/
        draw.setOnAction(e->{
            canvas.setCursor(new ImageCursor(pencil));
        });

        erase.setOnAction(e->{
            canvas.setCursor(new ImageCursor(rubber));
        });

        /*------------Changing line width-----------*/
        lineWidt1.setOnAction(e->{
                gc.setLineWidth(1);
                eraserSize = 5;
        });

        lineWidth2.setOnAction(e->{
                gc.setLineWidth(4);
                eraserSize = 10;
        });

        lineWidth3.setOnAction(e->{
                gc.setLineWidth(6);
                eraserSize = 15;
        });

        lineWidth4.setOnAction(e->{
                gc.setLineWidth(10);
                eraserSize = 20;
        });
    }
}