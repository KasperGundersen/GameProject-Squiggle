package Components;

import Database.DBConnection;
import Scenes.MainScene;
import Scenes.SignUp;
import Scenes.LogIn;
import Scenes.Scenes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class Registration {

    public static void registerUser(Connection con, String userName, String password, String userEmail, int avatarID) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO USERS VALUES (0, \"" + userName + "\", \"" + password + "\", NULL, \"" + userEmail + "\", " + avatarID + ", 0)");
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void submit(){
        Connection con = DBConnection.getCon();
        String username = SignUp.getName();
        String mail = SignUp.getMail();
        String password = SignUp.getPassword();

        if((DBConnection.exists(con,"userName", username))||(DBConnection.exists(con,"userMail", mail))){
            System.out.println("Brukernavn eller epost er allerede registrert");
        }else{
            Registration.registerUser(con, username, password, mail, 0);
        }
        MainScene.setScene(MainScene.li.getSc());
    }
}
