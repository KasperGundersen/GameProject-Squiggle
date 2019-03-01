package Components;

import Database.DBConnection;
import Scenes.MainScene;
import Scenes.SignUp;
import Scenes.LogIn;

import java.lang.reflect.AnnotatedType;
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
        //Encrypt password
        String encryptor = Encryptor.Encryptor(password,null);
        //get salt and hash
        String hash = Encryptor.getHash(encryptor);
        String salt = Encryptor.getSalt(encryptor);
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
        //Getting salt from db using username
        String salt = DBConnection.getSalt(con, username);
        //generating hash using salt
        String encryptor = Encryptor.Encryptor(password, salt);
        String hash = Encryptor.getHash(encryptor);
        //Check if
        if((DBConnection.exists(con,"userName", username))&&(DBConnection.exists(con,"password", hash))) {
            MainScene.setScene(MainScene.mm.getSc());
            DBConnection.setLoggedIn(con, username, 1);
        }
    }
}
