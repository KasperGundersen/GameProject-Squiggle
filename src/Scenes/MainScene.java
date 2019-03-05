package Scenes;

import Components.Toast;
import Components.UserInfo;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScene {

    private static final double HEIGHT = 600;
    private static final double WIDTH = 1000;

    private static Stage stage;

    static Scenes li = new LogIn(WIDTH, HEIGHT);
    static Scenes mm = new MainMenu(WIDTH, HEIGHT);
    static Scenes sq = new Squiggle(WIDTH, HEIGHT);
    static Scenes su = new SignUp(WIDTH, HEIGHT);
    static Scenes mp = new MyPage(WIDTH, HEIGHT);
    // User
    public static UserInfo user = new UserInfo();
    public static Toast toast = new Toast(stage, WIDTH, HEIGHT);

    public static void setScene(Scene sc) {
        stage.setScene(sc);
    }

    public void initialize(Stage stage) {
        MainScene.stage = stage;
        MainScene.stage.setTitle("Squiggle");
        MainScene.stage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        setScene(li.getSc());
        MainScene.stage.show();
    }
    private void closeProgram(){
        if(ConfirmBox.display("Warning!", "Sure you want to exit?")){
            stage.close();
        }
    }
    static void closeStage() {
        stage.close();
    }
}
