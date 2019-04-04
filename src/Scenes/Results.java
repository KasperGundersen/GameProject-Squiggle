package Scenes;

import Database.DBConnection;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
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
    private static Label placementLbl;
    private static ArrayList<Integer> points = DBConnection.getPointsList();
    private static ArrayList<Integer> players = DBConnection.getPlayersList();
    private static ArrayList<String> names = DBConnection.fromIDtoName(players);
    private static Button mmBtn;


    public Results(double WIDTH, double HEIGHT){
        super(WIDTH, HEIGHT);
        addUIControls(getGp());
    }

    public void addUIControls(GridPane gp){
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
            placementLbl = new Label(Integer.toString(i+1));
            placementLbl.setFont(Font.font("Arial", FontWeight.BOLD, 24));


            hboxes[i].getChildren().addAll(placementLbl, userIDLbl, pointsLbl);
            gp.add(hboxes[i], 1, i+1, 1, 1);
            gp.setHalignment(hboxes[i], HPos.CENTER);
            gp.setValignment(hboxes[i], VPos.CENTER);
            gp.add(images[i], 0, i+1, 1, 1);
        }

        mmBtn = new Button("Main menu");
        gp.add(mmBtn, 1, 5, 1, 1);
        mmBtn.setOnAction(e -> {
            MainScene.mm = new MainMenu(super.getWIDTH(), super.getHEIGHT());
            MainScene.setScene(MainScene.mm);
            MainScene.rs = null;
        });
    }

    public static ArrayList<String> getNamesFromID(){
        for(int i = 0; i < players.size(); i++){
            String name = DBConnection.getUsername(players.get(i));
            names.add(name);
        }
        return names;
    }
}
