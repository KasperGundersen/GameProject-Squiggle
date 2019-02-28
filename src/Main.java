import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double HEIGHT = 600;
    private static final double WIDTH = 1000;

    public static Stage window;
    public static Scenes li = new LogIn(WIDTH, HEIGHT);
    public static Scenes su = new SignUp(WIDTH, HEIGHT);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Squiggle");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
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
