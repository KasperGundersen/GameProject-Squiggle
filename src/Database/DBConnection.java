/* This class has static methods so we wont have to make objects of this class to use its methods */
package Database;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    // All information needed to connect to the database
    private static final String username = "zuimran";
    private static final String password = "xaXIMlNC";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String dBUrl = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;

    // Standard JDBC components
    private static Connection con;
    private static Statement stmt;
    private static ResultSet res;
    private static ResultSetMetaData rsmd;

    // Use this whenever you want to connect to the database
    public static Connection getCon() {
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(dBUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    // This method looks for "input" in the given column in the database
    public static boolean exists(Connection con, String columnName, String input) {
        try{
            String query = "SELECT " + columnName + " FROM USERS WHERE " + columnName + "= ?;";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, input);
            res = prepStmt.executeQuery();
            return res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setLoggedIn(Connection con, String username, int loggedIn) {
        try {
            String query = "UPDATE USERS SET loggedIn=? WHERE userName=?;";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setInt (1, loggedIn);
            prepStmt.setString(2, username);
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getSalt(Connection con, String username) {
        try {
            String query = "SELECT salt FROM USERS WHERE userName=?;";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, username);
            res = prepStmt.executeQuery();
            if(res.next()) {
                String salt = res.getString("salt");
                return salt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getLoggedIn(Connection con, String username) {
        boolean loggedIn = false;
        try {
            String query = "SELECT loggedIn FROM USERS WHERE userName=?;";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, username);
            res = prepStmt.executeQuery();
            res.next();
            int num = res.getInt("loggedIn");
            loggedIn = (num == 0 ? false : true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedIn;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setAvatarID(Connection con, int userID, int index) {
        try {
            String query = "UPDATE USERS SET avatarID=? WHERE UserID=?";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, index);
            prepStmt.setInt(2, userID);
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getWords(Connection con, String category) {
        try {
            String query = "SELECT word FROM LIBRARY WHERE category=?;";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, category);
            res = prepStmt.executeQuery();
            ArrayList<String> wordList = new ArrayList<>();
            while(res.next()) {
                wordList.add(res.getString("word"));
                return wordList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static int getUserID(Connection con, String username) {

        try {
            String query = "SELECT UserID FROM USERS WHERE userName=?;";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setString (1, username);
            res = prepStmt.executeQuery();
            if (res.next()) {
                return res.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}