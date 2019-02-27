import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double HEIGHT = 600;
    private static final double WIDTH = 1000;
    Stage window;
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

        Pane layout = new Pane();
        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(scene);
        window.show();


    }

    private void closeProgram(){
        Boolean answer = ConfirmBox.display("Feilmelding!!", "Sure you want to exit?");
        if(answer){
            window.close();
        }
    }
}
