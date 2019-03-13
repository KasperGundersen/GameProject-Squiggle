package Components;

import Database.DBConnection;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import javax.swing.text.html.ListView;
import java.io.File;
import java.util.ArrayList;

public class AvatarListView {
    private ArrayList<Player> players = null;//DBConnection.getAvatarID();
    private int amtPlayers = players.size();
    private Image[] listOfImages = new Image[players.size()];
   // private ListView<> listView;
    private ObservableList<String> users;

    public AvatarListView() {
        for(int i = 0; i<amtPlayers;i++ ){
            listOfImages[i] = getAvatar(players.get(i).getAvatarID());
        }
    }


    public Image getAvatar(int i){
        File file = new File("resources/avatars/" + i + ".jpg");
        return new javafx.scene.image.Image(file.toURI().toString());
    }
}
