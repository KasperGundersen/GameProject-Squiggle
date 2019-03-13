package Components;

import Database.DBConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class AvatarListView extends Application {
    private ArrayList<Player> players = DBConnection.getPlayers();
    private int amtPlayers = players.size();


    @Override
    public void start(Stage stage){
        Image[] listOfImages = fillListOfImage();
        ListView<String> listView = new ListView<String>();
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
                            imageView.setImage(listOfImages[i]);
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                        }
                        setText(players.get(i).getUsername());
                        setGraphic(imageView);
                    }
                }
            }
        });
        VBox box = new VBox(listView);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 400, 400);
        stage.setScene(scene);
        stage.show();
    }


    public ObservableList<String> usernamesAndPoints(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for(int i = 0; i<amtPlayers; i++){
            list.add(players.get(i).getUsername() + ": " + players.get(i).getPoints());
            return list;
        }
        return null;
    }

    public Image[] fillListOfImage(){
        Image[] list = new Image[players.size()]  ;
        for(int i = 0; i<amtPlayers;i++ ){
            list[i] = getAvatar(players.get(i).getAvatarID());
            return list;
        }
        return null;
    }


    public Image getAvatar(int i){
        File file = new File("resources/avatars/" + i + ".jpg");
        Image image = new Image(file.toURI().toString());
        return image;
    }
}
