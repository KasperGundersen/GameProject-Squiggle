import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double HEIGHT = 600;
    private static final double WIDTH = 1000;
    public static Stage window;
    public static LogIn li = new LogIn(WIDTH, HEIGHT);
    public static SignUp su = new SignUp(WIDTH, HEIGHT);

    public static void main(String[] args) {
        launch(args);
    }

    public static double getWidth() {
        return WIDTH;
    }

    public static double getHeight() {
        return HEIGHT;
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Squiggle");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        Pane layout = new Pane();

        setScene2(li.getSc());
        window.show();


    }

    private void closeProgram(){
        Boolean answer = ConfirmBox.display("Warning!", "Sure you want to exit?");
        if(answer){
            window.close();
        }
    }

    public static void setScene2(Scene sc) {
        window.setScene(sc);
    }
}
