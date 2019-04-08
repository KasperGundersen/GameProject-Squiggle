package Scenes;

import Components.Player;
import Components.UserInfo;
import Database.DBConnection;
import css.Css;
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
import java.util.Collection;
import java.util.Collections;

/**
 * Class thats show the result of the game after a game is playes
 */
public class Results extends Scenes{
    private static Label header;
    private static Label userIDLbl;
    private static Label pointsLbl;
    private static Label placementLbl;
    private static ArrayList<Player> players = DBConnection.getPlayers();
    private static Button mmBtn;

    public Results(double WIDTH, double HEIGHT){
        super(WIDTH, HEIGHT);
        addUIControls(getGp());
    }

    /**
     * Method that creats the scene
     * @param gp extendes the gridpane from the MainScene
     */
    public void addUIControls(GridPane gp){
        header = new Label("Results");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        header.setStyle("-fx-text-fill: white");
        gp.add(header, 1, 0, 1, 1);
        gp.setHalignment(header, HPos.CENTER);
        gp.setValignment(header, VPos.CENTER);
        gp.setGridLinesVisible(false);


        Collections.sort(players);

        if (players.get(0).getUserID() == UserInfo.getUserID()) {
            DBConnection.updateStats(true);
        } else {
            DBConnection.updateStats(false);
        }
        HBox hboxes[] = new HBox[players.size()];
        ImageView[] images = new ImageView[players.size()];

        for(int i = 0; i < players.size(); i++){
            hboxes[i] = new HBox(15);
            int avatarID = players.get(i).getAvatarID();
            images[i] = new ImageView(getAvatar(avatarID));
            images[i].setFitWidth(50);
            images[i].setFitHeight(50);

            userIDLbl = new Label("Player: " + players.get(i).getUsername());
            userIDLbl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            userIDLbl.setStyle("-fx-text-fill: white");
            pointsLbl = new Label("Points: " + players.get(i).getPoints());
            pointsLbl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            pointsLbl.setStyle("-fx-text-fill: white");
            placementLbl = new Label(Integer.toString(i+1));
            placementLbl.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            placementLbl.setStyle("-fx-text-fill: white");

            hboxes[i].getChildren().addAll(placementLbl, userIDLbl, pointsLbl);
            gp.add(hboxes[i], 1, i+1, 1, 1);
            gp.setHalignment(hboxes[i], HPos.CENTER);
            gp.setValignment(hboxes[i], VPos.CENTER);
            gp.add(images[i], 0, i+1, 1, 1);
        }

        mmBtn = new Button("Main menu");
        Css.buttonStyleRed(mmBtn);
        gp.add(mmBtn, 1, 5, 1, 1);
        mmBtn.setOnAction(e -> {
            DBConnection.exitGame();
            MainScene.mm = new MainMenu(super.getWIDTH(), super.getHEIGHT());
            MainScene.setScene(MainScene.mm);
            MainScene.rs = null;
        });
    }
}
