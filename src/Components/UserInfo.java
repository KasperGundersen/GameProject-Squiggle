package Components;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInfo {
    // Information about user - gets updated once a user logs in
    private static String userName;
    private static int userID;
    private static String colorHex;
    private static boolean soundOn;
    private static int avatarID;
    private static boolean drawing;
    private static int fontSize;

    public UserInfo() {
        this.userName = null;
        this.userID = 0;
        this.colorHex = null;
        this.soundOn = true;
        this.avatarID = 0;
        this.drawing = false;
        this.fontSize = 16;
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

    public static int getFontSize() {
        return fontSize;
    }

    public static void setFontSize(int fontSize) {
        UserInfo.fontSize = fontSize;
    }

    // Method that runs on login, updates UserInfo.userID variable and fetches avatarID from given user
    public static void initializeUser(int userId) {
        userID = userId;
        DBConnection.updateAvatarID(userId);
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




}
