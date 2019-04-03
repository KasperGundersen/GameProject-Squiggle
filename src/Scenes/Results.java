package Scenes;

import Database.DBConnection;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class Results extends Scenes{
    private static Label header;
    private static Label userIDLbl;
    private static Label pointsLbl;
    private static Label guessesLbl;
    private static ArrayList<Integer> points = DBConnection.getPointsList();
    private static ArrayList<Integer> players = DBConnection.getPlayersList();
    private static ArrayList<Integer> guesses = DBConnection.getGuessesList();
    private static ArrayList<String> names = getNamesFromID();


    public Results(double WIDTH, double HEIGHT){
        super(WIDTH, HEIGHT);
        addUIControls(getGp());
    }

    public static void addUIControls(GridPane gp){
        header = new Label("Results");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        gp.add(header, 1, 0, 1, 1);
        gp.setHalignment(header, HPos.CENTER);
        gp.setValignment(header, VPos.CENTER);
        gp.setGridLinesVisible(false);


        HBox hboxes[] = new HBox[players.size()];
        ImageView[] images = new ImageView[players.size()];

        for(int i = 0; i < players.size(); i++){
            hboxes[i] = new HBox(15);
            int userID = players.get(i);
            int avatarID = DBConnection.getAvatarID(userID);
            images[i] = new ImageView(getAvatar(avatarID));
            images[i].setFitWidth(50);
            images[i].setFitHeight(50);


            userIDLbl = new Label("Player: " + names.get(i));
            userIDLbl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            pointsLbl = new Label("Points: " + points.get(i));
            pointsLbl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            guessesLbl = new Label("Correct guesses: " + guesses.get(i));
            guessesLbl.setFont(Font.font("Arial", FontWeight.BOLD, 24));

            hboxes[i].getChildren().addAll(userIDLbl, pointsLbl, guessesLbl);
            gp.add(hboxes[i], 1, i+1, 1, 1);
            gp.setHalignment(hboxes[i], HPos.CENTER);
            gp.setValignment(hboxes[i], VPos.CENTER);
            gp.add(images[i], 0, i+1, 1, 1);
        }
    }

    public static ArrayList<String> getNamesFromID(){
        for(int i = 0; i < players.size(); i++){
            String name = DBConnection.getUsername(players.get(i));
            names.add(name);
        }
        return names;
    }
}
