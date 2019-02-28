package Scenes;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MyPage extends Scenes{
    private Button button1;


    public MyPage(double WIDTH, double HEIGHT){
        super(WIDTH, HEIGHT);
        addUIControls(super.getGP());

    }

    private void addUIControls(GridPane gridPane){

        button1 = new Button("Click me");
        button1.setPrefHeight(40);
        button1.setDefaultButton(true);
        button1.setPrefWidth(100);
        gridPane.add(button1, 0, 5, 2, 1);
        GridPane.setHalignment(button1, HPos.CENTER);
        GridPane.setValignment(button1, VPos.CENTER);
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        mainScene.initialize(stage);
    }


}
