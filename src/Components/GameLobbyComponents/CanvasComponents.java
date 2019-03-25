package Components.GameLobbyComponents;

import Components.UserInfo;
import Database.DBConnection;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

/**
 * Class that deals with teh canvas ingame
 */
public class CanvasComponents {
    private static ToggleButton draw;
    private static ToggleButton erase;
    private static ColorPicker cp;
    public static Canvas canvas;
    private static GraphicsContext gc;

    private static int WIDTH = 600, HEIGHT = 450;
    private static Timer timer;
    private static Timer timer2;
    private static Color color = Color.rgb(244,244,244);

    /**
     * Adds the drawing UI at the bottom of the display
     *
     * @return HBOX HorisontaloBox with all the buttons and options
     */
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

        hb.getChildren().addAll(draw, erase, cp, lineWidth1, lineWidth2, lineWidth3, lineWidth4);
        // hb.setStyle("-fx-background-color: #999");
        hb.setPrefWidth(60);
        hb.setAlignment(Pos.CENTER);
        //////////////////////////////////


        File pencilFile = new File("resources/icons/pencil.png");
        Image pencil = new Image(pencilFile.toURI().toString());
        File rubberFile = new File("resources/icons/rubber.png");
        Image rubber = new Image(rubberFile.toURI().toString());
        ImageCursor penCur = new ImageCursor(pencil, 40, pencil.getHeight()-40);
        ImageCursor rubCur = new ImageCursor(rubber,10,rubber.getHeight()-80);

        cp.setOnAction(e-> {
            cp.setValue(cp.getValue());
        });

        draw.setOnAction(e->{
            canvas.setCursor(penCur);
        });
        erase.setOnAction(e->{
            canvas.setCursor(rubCur);
            gc.setStroke(color);
        });

        lineWidth1.setOnAction(e->{
            gc.setLineWidth(1);
        });
        lineWidth2.setOnAction(e->{
            gc.setLineWidth(4);
        });
        lineWidth3.setOnAction(e->{
            gc.setLineWidth(6);
        });
        lineWidth4.setOnAction(e->{
            gc.setLineWidth(10);
        });
        return hb;
    }

    /**
     * Adds the canvas itself, where the drawing/viewing is done.
     * @return HBox HorizontalBox with the canvas
     */
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
        gc.setLineWidth(1);
        hb.getChildren().addAll(canvas);
        if(UserInfo.getDrawing()) {
            uploadImage();
            DBConnection.setRandomWord();
        }else{
            setImage();
        }

        //////////////////////////////////////////////
        if (UserInfo.getDrawing()) {

            canvas.setOnMousePressed(e -> {
                if (draw.isSelected()) {
                    gc.setStroke(cp.getValue());
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
            });
        }
        return hb;
    }


    //////////// Here begins code that deals with uploading canvas to DB ///////////////

    /**
     * Main upload method. Uploads drawing as bytes
     */
    public static void uploadImage(){
        if (UserInfo.getDrawing()) {
            WritableImage wim = canvasSnapshot(canvas);
            byte[] blob = imageToByte(wim);
            DBConnection.uploadImage(blob, "insertWord");
        }
    }

    /**
     * Uploads an updated version of drawing to Database
     */
    public static void updateImage() {
        if (UserInfo.getDrawing()) {
            Service<Void> service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            //Background work
                            final CountDownLatch latch = new CountDownLatch(1);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        WritableImage wim = canvasSnapshot(canvas);
                                        new Thread(()->{
                                            DBConnection.updateImage(imageToByte(wim));
                                        }).start();
                                    }finally{
                                        latch.countDown();
                                    }
                                }
                            });
                            latch.await();
                            //Keep with the background work
                            return null;
                        }
                    };
                }
            };
            service.start();
        }
    }

    /**
     * snapshots the canvas and returns WritableImage
     * @return WritableImage Image of the canvas
     */
    private static WritableImage canvasSnapshot(Canvas canvas) {
        WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
        SnapshotParameters spa = new SnapshotParameters();
        return canvas.snapshot(spa, writableImage);
    }

    /**
     * Method that turns image into byte[], this is then uploaded as blob
     * @param image WritableImage that is to be uploaded to database
     * @return byte[] List of bytes that can be uploaded
     */
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

    /**
     * Converts blob back to image, paints this at canvas
     */
    public static void setImage(){
        if (!UserInfo.getDrawing()) {
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

    public static void blobToImage(){

    }
}
