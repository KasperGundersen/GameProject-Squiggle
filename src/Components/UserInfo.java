package Components;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserInfo {
    private static int userID;
    private static String colorHex;
    private static boolean soundOn;
    private static int avatarID;

    public UserInfo(int userID) {
        this.userID = userID;
        this.colorHex = null;
        this.soundOn = true;
        updateAvatarID(userID);
    }

    public static int getUserID() {
        return userID;
    }

    public static int getAvatarID() {
        return avatarID;
    }

    public static void updateAvatarID(int userID) {
        Connection con = DBConnection.getCon();
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT avatarID FROM USERS WHERE userID=\"" + userID + "\";");
            if (res.next()) {
                this.avatarID = res.getInt("avatarID");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
