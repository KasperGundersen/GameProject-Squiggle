/* This class has static methods so we wont have to make objects of this class to use its methods */
package Database;

import Components.UserInfo;
import Scenes.Squiggle;

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
    private static ResultSet res;

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

    // Method that registers a user
    public static void registerUser(String userName, String hash, String salt, String userEmail, int avatarID) {
        Connection con = getCon();
        try {
            String query = "INSERT INTO USERS VALUES (0, ?, ?, ?, ?, ?, 0)";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, userName);
            prepStmt.setString(2, hash);
            prepStmt.setString(3, salt);
            prepStmt.setString(4, userEmail);
            prepStmt.setInt(5, avatarID);
            prepStmt.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(con);
    }

    // This method looks for "input" in the given column in the database
    public static boolean exists(String columnName, String input) {
        Connection con = getCon();
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
        closeConnection(con);
        return false;
    }

    // Makes a user show as logged in when logged in
    public static void setLoggedIn(String username, int loggedIn) {
        Connection con = getCon();
        try {
            String query = "UPDATE USERS SET loggedIn=? WHERE userName=?;";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setInt (1, loggedIn);
            prepStmt.setString(2, username);
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        closeConnection(con);
    }

    // Gets salt, used for comparing passwords
    public static String getSalt(String username) {
        Connection con = getCon();
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
        closeConnection(con);
        return null;
    }

    // For seing if a user is already logged in or not
    public static boolean getLoggedIn(String username) {
        boolean loggedIn = false;
        Connection con = getCon();
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
        closeConnection(con);
        return loggedIn;
    }

    // General method for closing a connection, is to be used everytime getCon() is used
    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sets avatarID in the database, making the user have same avatarID on next LogIn
    public static void setAvatarID(int userID, int index) {
        Connection con = getCon();
        try {
            String query = "UPDATE USERS SET avatarID=? WHERE UserID=?";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, index);
            prepStmt.setInt(2, userID);
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(con);
    }

    public static ArrayList<String> getWords(String category) {
        Connection con = getCon();
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
        closeConnection(con);
        return null;

    }


    // Fetches userID given username, used upon initialization of user, log in
    public static int getUserID(String username) {
        Connection con = getCon();
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
        closeConnection(con);
        return 0;
    }


    // Method that runs on "Join Game", sets drawing to 1, if no one else is ingame
    public static void setDrawer() {
        Connection con = getCon();
        try {
            String query = "SELECT * FROM GAME WHERE drawing=1";
            PreparedStatement prepStmt = con.prepareStatement(query);
            res = prepStmt.executeQuery();
            if (res.next()) {
                UserInfo.setDrawing(false);
            } else {
                String query2 = "UPDATE GAME SET drawing=1 WHERE userID=?";
                prepStmt = con.prepareStatement(query2);
                prepStmt.setInt(1, UserInfo.getUserID());
                prepStmt.executeUpdate();
                UserInfo.setDrawing(true);
            }
        } catch(SQLException e ) {
            e.printStackTrace();
        }
        closeConnection(con);
    }

    public static void enterGame() {
        Connection con = getCon();
        try {
            String query = "INSERT INTO GAME VALUES (?, ?, ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, UserInfo.getUserID());
            prepStmt.setInt(2, 0);
            prepStmt.setInt(3, 0);
            prepStmt.setInt(4, 0);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(con);
    }

    public static void exitGame() {
        Connection con = getCon();
        try {
            String query = "DELETE FROM GAME WHERE userID = ?";
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, UserInfo.getUserID());
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        closeConnection(con);
    }


    public static void insertIntoDB(String words) {
        Connection con = getCon();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO LIBRARY VALUE (default, \"" +  words + "\");");
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(con);
    }
}