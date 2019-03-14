package Components.GameLobbyComponents;

import Components.Player;
import Database.DBConnection;
import Scenes.GameLobby;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class AvatarComponents {
    public static ListView<String> listView;
    public static ArrayList<Player> players;
    public static Timer timer3;
    public static ObservableList data = FXCollections.observableArrayList();

    public static VBox addAvatarUI() {
        VBox vb = new VBox();
        listView = new ListView<>();
        setIntoLV();
        listView.setItems(data);
        vb.getChildren().add(listView);
        timer3();
        return vb;
    }

    private static Image getAvatar ( int i){
        File file = new File("resources/avatars/" + i + ".jpg");
        Image image = new Image(file.toURI().toString());
        return image;
    }

    private static void timer3(){
        timer3 = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                updateData();
            }
        };
        timer3.schedule(task, 0, +5000);
    }

    public static void turnOfTimer3() {
        if (timer3 != null) {
            timer3.cancel();
        }
    }

    private static void setIntoLV(){
        players = DBConnection.getPlayers();
        for(Player p : players) {
            if(exists(p.getUsername())) {
                data.add(p.getUsername());
            }
        }
        listView.setCellFactory(param -> new ListCell<>() {
            private ImageView iv = new ImageView();
            @Override
            public void updateItem(String userName, boolean empty) {
                super.updateItem(userName, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    for(Player p : players){
                        if(userName.equals(p.getUsername())){
                            iv.setImage(getAvatar(p.getAvatarID()));
                            iv.setFitHeight(50);
                            iv.setFitWidth(50);
                        }
                        setText(userName + ", score: " + p.getPoints());
                        setGraphic(iv);
                    }
                }
            }
        });
    }

    private static void updateData() {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        //Background work
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    players = DBConnection.getPlayers();
                                    setIntoLV();
                                    listView.refresh();
                                    System.out.println(players.toString());
                                }finally{
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        //Keep with the background work
                        return null;
                    }
                };
            }
        };
        service.start();
    }

    private static boolean exists(String s){
        for (Object o : data){
            if(o.equals(s)){
                return false;
            }
        }
        return true;
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

