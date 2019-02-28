import Scenes.LogIn;
import Scenes.MainScene;
import Scenes.SignUp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    private MainScene mainScene = new MainScene();
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        mainScene.initialize(stage);
    }
}
