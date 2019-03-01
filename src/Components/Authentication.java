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
        String hash;
        String salt;

        if(password != null) {
            //Encrypt password
            String encryptor = Encryptor.Encryptor(password, null);
            //get salt and hash
            hash = Encryptor.getHash(encryptor);
            salt = Encryptor.getSalt(encryptor);
        }else{
            SignUp.visiablePassword(true);
            hash = null;
            salt = null;
        }
        System.out.println(username + "..." + mail + "..." + password + " hei");

        if(username == null){
            SignUp.visiableEmptyUser(true);
            System.out.println("u");
        } else {
            SignUp.visiableEmptyUser(false);
            System.out.println("e");
        }

        if(mail == null) {
            SignUp.visiableEmptyMail(true);
            System.out.println("d");
        } else {
            SignUp.visiableEmptyMail(false);
            System.out.println("c");
        }

        if(password == null){
            SignUp.visiableEmptyPassword(true);
            System.out.println("a");
        } else {
            SignUp.visiableEmptyPassword(false);
            System.out.println("b");
        }

        if((DBConnection.exists(con,"userName", username))||(DBConnection.exists(con,"userMail", mail))) {
            SignUp.visiableUserMail(true);
        }else if(username != null && mail != null && hash != null && salt != null) {
            registerUser(con, username, hash, salt, mail, 0);
            MainScene.setScene(MainScene.li.getSc());
        }else{
            SignUp.visiableUserMail(false);
        }
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
            if (!DBConnection.getLoggedIn(con, username)) {
                MainScene.setScene(MainScene.mm.getSc());
                DBConnection.setLoggedIn(con, username, 1);
            } else {
                // insert showLabel method
            }
        }
    }
}
