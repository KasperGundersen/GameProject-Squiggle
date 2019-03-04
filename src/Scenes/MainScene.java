package Scenes;

import Components.UserInfo;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScene {

    private static final double HEIGHT = 600;
    private static final double WIDTH = 1000;

    public static Stage window;

    public static Scenes li = new LogIn(WIDTH, HEIGHT);
    public static Scenes mm = new MainMenu(WIDTH, HEIGHT);
    public static Scenes sq = new Squiggle(WIDTH,HEIGHT);
    public static Scenes su = new SignUp(WIDTH, HEIGHT);

    public static Scenes mp = new MyPage(WIDTH, HEIGHT);

    // User
    public static UserInfo user = new UserInfo();

    public static void setScene(Scene sc) {
        window.setScene(sc);
    }

    public void initialize(Stage stage) {
        window = stage;
        window.setTitle("Squiggle");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        setScene(li.getSc());
        window.show();
    }

    private void closeProgram(){
        Boolean answer = ConfirmBox.display("Warning!", "Sure you want to exit?");
        if(answer){
            window.close();
        }
    }

    public static void closeStage() {
        window.close();
    }
}
