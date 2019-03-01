package Components;

import Database.DBConnection;
import Scenes.MainScene;
import Scenes.SignUp;
import Scenes.LogIn;
import Scenes.Scenes;
import com.mysql.cj.log.Log;
import com.sun.tools.javac.Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class Authentication {

    public static void registerUser(Connection con, String userName, String hash, String salt, String userEmail, int avatarID) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO USERS VALUES (0, \"" + userName + "\", \"" + hash + "\", \"" + salt + "\", \"" + userEmail + "\", " + avatarID + ", 0)");
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
        password = Encryptor.Encryptor(password,null);
        int splitt = password.indexOf('|');
        String hash = password.substring(0, splitt);
        String salt = password.substring(splitt+1, password.length());

        if((DBConnection.exists(con,"userName", username))||(DBConnection.exists(con,"userMail", mail))) {
            System.out.println("This username or email is already registered");
        }else if(username == null || mail == null || password == null) {
            System.out.println("Username, password, or email cannot be empty");
        }else{
            registerUser(con, username, hash, salt, mail, 0);
        }
        MainScene.setScene(MainScene.li.getSc());
    }

    public static void logIn() {
        Connection con = DBConnection.getCon();
        String username = LogIn.getUserName();
        String password = LogIn.getPassword();

        if((DBConnection.exists(con,"userName", username))&&(DBConnection.exists(con,"password", password))) {
            MainScene.setScene(MainScene.mm.getSc());
        }
    }
}
