package Components;

import Database.DBConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.paint.Color;

public class UserInfo {
    // Information about user - gets updated once a user logs in
    private static String userName;
    private static int userID;
    private static String colorHex;
    private static boolean soundOn;
    private static int avatarID;
    private static boolean drawing;
    private static int fontSize;
    private static Color color;
    private static String userEmail;


    public UserInfo() {
        this.userName = null;
        this.userID = 0;
        this.colorHex = null;
        this.soundOn = true;
        this.avatarID = 0;
        this.drawing = false;
        this.fontSize = 16;
        this.color = new Color(1,1,1,1);

        this.userEmail = null;
    }

    // getters
    public static String getUserName() {
        return userName;
    }

    public static int getUserID() {
        return userID;
    }

    public static int getAvatarID() {
        return avatarID;
    }

    public static boolean getDrawing() {
        return drawing;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    // Method that runs on login, updates UserInfo.userID variable and fetches avatarID from given user
    public static void initializeUser(int userId) {
        userID = userId;
        DBConnection.updateAvatarID(userId);
        userEmail = DBConnection.getUserEmail(userId);
    }

    public static void setUserName(String newName) {
        userName = newName;
    }

    public static void setDrawing(boolean bool) {
        drawing = bool;
    }

    public static void setAvatarID(int newID) {
        avatarID = newID;
    }

    public static void setFontSize(int fontSize) {
        UserInfo.fontSize = fontSize;
    }

    public static int getFontSize() {
        return fontSize;
    }

    public static Color getColor() {
        return color;
    }

    public static void setColor(Color color) {
        UserInfo.color = color;
    }
}
