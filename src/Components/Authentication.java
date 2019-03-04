package Components;

import Database.DBConnection;
import Scenes.MainScene;
import Scenes.SignUp;
import Scenes.LogIn;

import javax.security.auth.login.LoginContext;
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
            SignUp.visiblePassword(true);
            hash = null;
            salt = null;
        }

        if(username == null){
            SignUp.visibleEmptyUser(true);
            System.out.println("u");
        } else if(username != null) {
            SignUp.visibleEmptyUser(false);
            System.out.println("e");
        }

        if(mail == null) {
            SignUp.visibleEmptyMail(true);
            System.out.println("d");
        } else if(mail != null){
            SignUp.visibleEmptyMail(false);
            System.out.println("c");
        }

        if(password == null){
            SignUp.visibleEmptyPassword(true);
            System.out.println("a");
        } else if(password != null){
            SignUp.visibleEmptyPassword(false);
            System.out.println("b");
        }

        if((DBConnection.exists(con,"userName", username))||(DBConnection.exists(con,"userMail", mail))) {
            SignUp.visibleUserMail(true);
        }else if((username != null) && (mail != null) && (hash != null) && (salt != null)) {
            registerUser(con, username, hash, salt, mail, 0);
            MainScene.setScene(MainScene.li.getSc());
        }else{
            SignUp.visibleUserMail(false);
        }
        DBConnection.closeConnection(con);
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
                SignUp.visibleEmptyPassword(false);
                MainScene.setScene(MainScene.mm.getSc());
                DBConnection.setLoggedIn(con, username, 1);
            } else {
                // already logged in error
                LogIn.setTextLoginError("User already logged in");
                LogIn.visibleLoginError(true);
            }
        } else {
            // wrong username or password error
            LogIn.setTextLoginError("Incorrect username or password");
            LogIn.visibleLoginError(true);
        }
        DBConnection.closeConnection(con);
    }
}
