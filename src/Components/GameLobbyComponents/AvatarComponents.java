package Components.GameLobbyComponents;

import Components.Player;
import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AvatarComponents {
    private static ArrayList<Player> players;
    private static int amtPlayers;
    private static ListView<String> listView = new ListView<String>();
    public static Timer timer3;


    public static VBox addAvatarUI() {
        VBox vb = new VBox();
        players = DBConnection.getPlayers();
        amtPlayers = players.size();
        // Image[] listOfImages = fillListOfImage();



        ObservableList data = FXCollections.observableArrayList();
        for (int i = 0; i < amtPlayers; i++) {
            data.add("");
        }


        listView.setItems(data);

        listView.setCellFactory(param -> new ListCell<String>() {
            private ImageView iv = new ImageView();

            @Override
            public void updateItem(String userName, boolean empty) {
                super.updateItem(userName, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    for (int i = 0; i < amtPlayers; i++) {
                        // if(userName.equals(players.get(i).getUsername())){
                        iv.setImage(getAvatar(players.get(i).getAvatarID()));
                        iv.setFitHeight(50);
                        iv.setFitWidth(50);
                        // }
                        setText(players.get(i).getUsername() + ", score: " + players.get(i).getPoints());
                        setGraphic(iv);
                    }
                }
            }
        });
        vb.getChildren().add(listView);
        return vb;
    }

    public static Image getAvatar ( int i){
        File file = new File("resources/avatars/" + i + ".jpg");
        Image image = new Image(file.toURI().toString());
        return image;
    }


    public static void timer3(){
        timer3 = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                players = DBConnection.getPlayers();
                listView.refresh();
            }
        };
        timer3.schedule(task, 0, +5000);
    }

    public static void turnOfTimer3() {
        if (timer3 != null) {
            timer3.cancel();
        }
    }
}












        /*
        ObservableList<String> nameAndPoints = usernamesAndPoints();
        listView.setItems(nameAndPoints);

        listView.setCellFactory(param -> new ListCell<String>(){
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String userName, boolean empty){
                super.updateItem(userName, empty);
                if(empty){
                    setText(null);
                    setGraphic(null);
                } else {
                    for(int i = 0; i<amtPlayers; i++){
                        if(userName.equals(players.get(i).getUsername())){
                            imageView.setImage(getAvatar(players.get(i).getAvatarID()));
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                        }
                        // setText(userName + ": " + players.get(i).getPoints());
                        // setGraphic(imageView);
                    }
                }
            }
        });
        vb.getChildren().add(listView);
        return vb;
    }
    public static ObservableList<String> usernamesAndPoints(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for(int i = 0; i<amtPlayers; i++){
            list.add(players.get(i).getUsername() + ": " + players.get(i).getPoints());
            return list;
        }
        return null;
    }

    public static Image[] fillListOfImage(){
        Image[] list = new Image[players.size()]  ;
        for(int i = 0; i<amtPlayers;i++ ){
            list[i] = getAvatar(players.get(i).getAvatarID());
            return list;
        }
        return null;
    }

    */

