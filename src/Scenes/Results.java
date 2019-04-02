package Scenes;

import Components.GameLobbyComponents.AvatarComponents;
import Components.Player;
import Components.UserInfo;
import Database.DBConnection;
import css.Css;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.util.ArrayList;

public class Results extends Scenes{
    private static Label header;
    private static Label userIDLbl;
    private static Label pointsLbl;
    private static Label guessesLbl;



    public Results(double WIDTH, double HEIGHT){
        super(WIDTH, HEIGHT);
        addUIControls(getGp());
    }

    public static void addUIControls(GridPane gp){
        header = new Label("Results");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gp.add(header, 1, 0, 1, 1);
        gp.setGridLinesVisible(true);


        ArrayList<Integer> points = DBConnection.getPointsList();
        ArrayList<Integer> players = DBConnection.getPlayersList();
        ArrayList<Integer> guesses = DBConnection.getGuessesList();
        int amtPlayers = players.size();

        HBox hboxes[] = new HBox[amtPlayers];

        for(int i = 0; i < amtPlayers; i++){
            hboxes[i] = new HBox(10);

            userIDLbl = new Label("User ID: " + players.get(i));
            userIDLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            pointsLbl = new Label("Points: " + points.get(i));
            pointsLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            guessesLbl = new Label("Correct guesses: " + guesses.get(i));
            guessesLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));

            hboxes[i].getChildren().addAll(userIDLbl, pointsLbl, guessesLbl);
            gp.add(hboxes[i], 1, i+1, 1, 1);
        }
    }
}
