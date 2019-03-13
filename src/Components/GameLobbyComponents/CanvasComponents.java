package Components.GameLobbyComponents;

import Components.UserInfo;
import Database.DBConnection;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class CanvasComponents {
    private static ToggleButton draw;
    private static ToggleButton erase;
    private static ColorPicker cp;
    public static Canvas canvas;
    private static GraphicsContext gc;
    public static ImageView imv;

    private static int eraserSize = 5;
    private static int WIDTH = 600, HEIGHT = 450;
    private static Timer timer;
    private static Color color = Color.rgb(244,244,244);

    //-----------Bottom-----------//
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

        ToggleButton lineWidth1 = new ToggleButton("1");
        ToggleButton lineWidth2 = new ToggleButton("2");
        ToggleButton lineWidth3 = new ToggleButton("3");
        ToggleButton lineWidth4 = new ToggleButton("4");
        ToggleButton[] penSize = {lineWidth1, lineWidth2, lineWidth3, lineWidth4};
        ToggleGroup tgLineWidth = new ToggleGroup();
        for (ToggleButton tb : penSize) {
            tb.setMinWidth(50);
            tb.setToggleGroup(tgLineWidth);
            tb.setCursor(Cursor.HAND);
        }
        tgLineWidth.selectToggle(lineWidth1);

        cp = new ColorPicker();
        cp.setValue(Color.BLACK);
        cp.setOnAction(e-> cp.setValue(cp.getValue()));

        hb.getChildren().addAll(draw, erase, cp, lineWidth1, lineWidth2, lineWidth3, lineWidth4);
        hb.setMargin(hb.getChildren().get(1),new Insets(0,0,0,10));
        hb.setStyle("-fx-background-color: #999");
        hb.setPrefWidth(60);
        hb.setAlignment(Pos.CENTER);
        //////////////////////////////////
        File pencilFile = new File("resources/icons/pencil.png");
        Image pencil = new Image(pencilFile.toURI().toString());
        File rubberFile = new File("resources/icons/rubber.png");
        Image rubber = new Image(rubberFile.toURI().toString());

        draw.setOnAction(e->{
            canvas.setCursor(new ImageCursor(pencil));
        });
        erase.setOnAction(e->{
            canvas.setCursor(new ImageCursor(rubber));
        });
        lineWidth1.setOnAction(e->{
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
        return hb;
    }

    //-----------Center-----------//

    public static HBox addCanvasUI(){
        HBox hb = new HBox();

        hb.setAlignment(Pos.CENTER);
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0,0,WIDTH, HEIGHT);
        gc.setFill(Color.AQUA);
        gc.setLineWidth(4);
        gc.strokeRect(0,0,WIDTH, HEIGHT);
        hb.getChildren().addAll(canvas);
        uploadImage();
        //////////////////////////////////////////////
        if (UserInfo.getDrawing()) {

            canvas.setOnMousePressed(e -> {
                if (draw.isSelected()) {
                    gc.setStroke(cp.getValue());
                } else if (erase.isSelected()) {
                    gc.setStroke(color);
                }
                gc.beginPath();
                gc.lineTo(e.getX(), e.getY());
            });
            canvas.setOnMouseDragged(e -> {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
            });
            canvas.setOnMouseReleased(e -> {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
                gc.closePath();
                updateImage();
            });
        }
        return hb;
    }

    public static void timer(){
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                setImage();
            }
        };
        timer.schedule(task, 0, +5000);
    }

    public static void turnOfTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    //////////// Here begins code that deals with uploading canvas to DB ///////////////
    // The main upload method
    private static void uploadImage(){
        WritableImage wim = canvasSnapshot(canvas);
        byte[] blob = imageToByte(wim);
        DBConnection.uploadImage(blob, "insertWord");
    }

    private static void updateImage(){
        Task<Void> t = new Task<>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    WritableImage wim = canvasSnapshot(canvas);
                    byte[] blob = imageToByte(wim);
                    DBConnection.updateImage(blob);
                });
                return null;
            }
        };
        Thread th = new Thread(t);
        th.setDaemon(true);
        th.start();
    }

    // Method that snapshots the canvas and returns WritableImage
    private static WritableImage canvasSnapshot(Canvas canvas) {
        WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
        SnapshotParameters spa = new SnapshotParameters();
        return canvas.snapshot(spa, writableImage);
    }

    // Method that turns image into byte[], this is then uploaded as blob
    private static byte[] imageToByte(WritableImage image) {
        BufferedImage bufferimage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferimage, "jpg", output );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toByteArray();
    }

    // Needs method for getting blob and converting back to image

    private static void setImage(){
        try {
            BufferedImage bi = ImageIO.read(DBConnection.getImage());
            if(bi != null){
              Image img = SwingFXUtils.toFXImage(bi, null);
              gc.drawImage(img, 0,0);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
