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

    public UserInfo() {
        this.userName = null;
        this.userID = 0;
        this.colorHex = null;
        this.soundOn = true;
        this.avatarID = 0;
        this.drawing = false;
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

    // Fetches avatarID from database, allows game to show the users avatar inGame using UserInfo.avatarID variable
    public static void updateAvatarID(int userID) {
        Connection con = DBConnection.getCon();
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT avatarID FROM USERS WHERE userID=\"" + userID + "\";");
            if (res.next()) {
                avatarID = res.getInt("avatarID");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        DBConnection.closeConnection(con);
    }

    // Method that runs on login, updates UserInfo.userID variable and fetches avatarID from given user
    public static void initializeUser(int userId) {
        userID = userId;
        updateAvatarID(userId);
    }

    public static void setUserName(String newName) {
        userName = newName;
    }

    public static void setDrawing(boolean bool) {
        drawing = bool;
    }
}
