package Components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.File;

public class CanvasComponents {

    private static ToggleButton draw;
    private static ToggleButton erase;

    private static ToggleButton lineWidth1;
    private static ToggleButton lineWidth2;
    private static ToggleButton lineWidth3;
    private static ToggleButton lineWidth4;

    private static int eraserSize;


    public static HBox addDrawingUI() {
        HBox hb = new HBox();

        draw = new ToggleButton("Draw");
        erase = new ToggleButton("Erase");

        ToggleButton[] tools = {draw, erase};

        ToggleGroup tgTools = new ToggleGroup();

        for (ToggleButton tool : tools) {
            tool.setMinWidth(50);
            tool.setToggleGroup(tgTools);
            tool.setCursor(Cursor.HAND);
        }
        tgTools.selectToggle(draw);

        lineWidth1 = new ToggleButton("1");
        lineWidth2 = new ToggleButton("2");
        lineWidth3 = new ToggleButton("3");
        lineWidth4 = new ToggleButton("4");

        ToggleButton[] penSize = {lineWidth1, lineWidth2, lineWidth3, lineWidth4};

        ToggleGroup tgLineWidth = new ToggleGroup();

        for (ToggleButton tb : penSize) {
            tb.setMinWidth(50);
            tb.setToggleGroup(tgLineWidth);
            tb.setCursor(Cursor.HAND);
        }

        tgLineWidth.selectToggle(lineWidth1);

        ColorPicker cp = new ColorPicker();
        cp.setValue(Color.BLACK);

        cp.setOnAction(e-> cp.setValue(cp.getValue()));

        hb.getChildren().addAll(draw, erase, cp, lineWidth1, lineWidth2, lineWidth3, lineWidth4);
        hb.setMargin(hb.getChildren().get(1),new Insets(0,0,0,10));
        hb.setStyle("-fx-background-color: #999");
        hb.setPrefWidth(60);
        hb.setAlignment(Pos.CENTER);
        return hb;
    }

    public static void drawingUIHandler() {
        File pencilFile = new File("resources/icons/pencil.png");
        Image pencil = new Image(pencilFile.toURI().toString());

        File rubberFile = new File("resources/icons/rubber.png");
        Image rubber = new Image(rubberFile.toURI().toString());

        lineWidth1.setOnAction(e -> {
            eraserSize = 5;
        });

        lineWidth2.setOnAction(e -> {
            eraserSize = 10;
        });

        lineWidth3.setOnAction(e -> {
            eraserSize = 15;
        });

        lineWidth4.setOnAction(e -> {
            eraserSize = 20;
        });
    }

}
