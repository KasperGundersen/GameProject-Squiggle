package Scenes;

import Components.GameLobbyComponents.AvatarComponents;
import Components.Player;
import Database.DBConnection;
import css.Css;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.util.ArrayList;

public class Results extends Scenes{
    private static Label header;



    public Results(double WIDTH, double HEIGHT){
        super(WIDTH, HEIGHT);
        addUIControls(getGp());
    }

    public static void addUIControls(GridPane gp){
        header = new Label("Results");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gp.add(header, 1, 1, 2, 1);
        gp.setGridLinesVisible(true);

        ArrayList<Integer> points = DBConnection.getPointsList();
        ArrayList<Integer> players = DBConnection.getPlayersList();
        int amtPlayers = players.size();


        HBox hboxes[] = new HBox[amtPlayers];
        for(int i = 0; i < amtPlayers; i++){
            hboxes[i] = new HBox();
            hboxes[i].getChildren().addAll(new Label(Integer.toString(players.get(i))),
                                           new Label(Integer.toString(points.get(i))));
            gp.add(hboxes[i], 1, i, 2, 1);
        }
    }
}
