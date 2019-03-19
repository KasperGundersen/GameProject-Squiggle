/* This class has static methods so we wont have to make objects of this class to use its methods */
package Database;

import Components.GameLobbyComponents.LiveChatComponents;
import Components.Player;
import Components.UserInfo;
import javafx.scene.image.WritableImage;
import javafx.scene.text.HitInfo;

import javax.sql.rowset.serial.SerialBlob;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DBConnection {

    // All information needed to connect to the database
    private static final String username = "zuimran";
    private static final String password = "xaXIMlNC";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String dBUrl = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;


    // Method that registers a user
    public static void registerUser(String userName, String hash, String salt, String userEmail, int avatarID) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            con = HikariCP.getCon();
            String query = "INSERT INTO USERS VALUES (0, ?, ?, ?, ?, ?, 0)";
            prepStmt = con.prepareStatement(query);
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
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }

    // This method looks for "input" in the given column in the database
    public static boolean exists(String columnName, String input) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try{
            con = HikariCP.getCon();
            String query = "SELECT " + columnName + " FROM USERS WHERE " + columnName + "=?;";
            prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, input);
            res = prepStmt.executeQuery();
            return res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return false;
    }

    // Makes a user show as logged in when logged in
    public static void setLoggedIn(String username, int loggedIn) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            con = HikariCP.getCon();
            String query = "UPDATE USERS SET loggedIn=? WHERE userName=?;";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt (1, loggedIn);
            prepStmt.setString(2, username);
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }

    // Gets salt, used for comparing passwords
    public static String getSalt(String username) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT salt FROM USERS WHERE userName=?;";
            prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, username);
            res = prepStmt.executeQuery();
            if(res.next()) {
                String salt = res.getString("salt");
                return salt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }

    // For seing if a user is already logged in or not
    public static boolean getLoggedIn(String username) {
        boolean loggedIn = false;
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT loggedIn FROM USERS WHERE userName=?;";
            prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, username);
            res = prepStmt.executeQuery();
            res.next();
            int num = res.getInt("loggedIn");
            loggedIn = (num == 0 ? false : true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return loggedIn;
    }

    // General method for closing a connection, is to be used everytime getCon() is used
    public static void closeConnection(Connection con, Statement stmt, ResultSet res) {
        try {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Sets avatarID in the database, making the user have same avatarID on next LogIn
    public static void setAvatarID(int userID, int index) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            con = HikariCP.getCon();
            String query = "UPDATE USERS SET avatarID=? WHERE userID=?";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, index);
            prepStmt.setInt(2, userID);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }

    public static ArrayList<String> getWords(String category) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT word FROM LIBRARY WHERE category=?;";
            prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, category);
            res = prepStmt.executeQuery();
            ArrayList<String> wordList = new ArrayList<>();
            while(res.next()) {
                wordList.add(res.getString("word"));
                return wordList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }


    // Fetches userID given username, used upon initialization of user, log in
    public static int getUserID(String username) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT userID FROM USERS WHERE userName=?;";
            prepStmt = con.prepareStatement(query);
            prepStmt.setString (1, username);
            res = prepStmt.executeQuery();
            if (res.next()) {
                return res.getInt("userID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return 0;
    }


    // Method that runs on "Join Game", sets drawing to 1, if no one else is ingame
    public static void setDrawer() {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT * FROM GAME WHERE drawing=1";
            prepStmt = con.prepareStatement(query);
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
        } finally {
            closeConnection(con, prepStmt, res);
        }
    }

    // Puts user in GAME table, where all users in a game are
    public static void enterGame() {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            con = HikariCP.getCon();
            String query = "INSERT INTO GAME VALUES (?, ?, ?, ?)";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, UserInfo.getUserID());
            prepStmt.setInt(2, 0);
            prepStmt.setInt(3, 0);
            prepStmt.setInt(4, 0);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }

    // Removes user from GAME table when user quits or game is over
    public static void exitGame() {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            con = HikariCP.getCon();
            String query = "DELETE FROM GAME WHERE userID = ?";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, UserInfo.getUserID());
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }


    public static void insertIntoDB(String words) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            con = HikariCP.getCon();
            String dropTable = ""
                    + "DROP TABLE LIBRARY;";
            String createTable = ""
                    + "CREATE TABLE LIBRARY("
                    + "word VARCHAR(30)"
                    + ");";
            prepStmt = con.prepareStatement(dropTable);
            prepStmt.executeUpdate();
            prepStmt = con.prepareStatement(createTable);
            prepStmt.executeUpdate();
            String insert = "INSERT INTO LIBRARY VALUE (\"" +  words + "\");";
            prepStmt = con.prepareStatement(insert);
            prepStmt.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }

    //Livechat methods start
    public static void insertMessage(String message) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            con = HikariCP.getCon();
            String query = "INSERT INTO CHAT VALUE (default, ?, ?);";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt (1, UserInfo.getUserID());
            prepStmt.setString (2, message);
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }

    public static ArrayList<String> getMessages() {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT input, userID FROM CHAT";
            prepStmt = con.prepareStatement(query);
            res = prepStmt.executeQuery();

            ArrayList<String> messages = new ArrayList<>();
            while (res.next()) {
                if (!(res.getString("input").equals(""))) {
                    int userId = res.getInt("userID");
                    messages.add(getUsername(userId) + ": " + res.getString("input"));
                }
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }

    public static ArrayList<String> getNewMessages() {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            int highestID = getHighestChatID();
            int tempHighestChatID = UserInfo.getTempHighestChatID();
            String query = "SELECT input, userID FROM CHAT where ChatID > " + tempHighestChatID + " and ChatID <= " + highestID + ";";
            prepStmt = con.prepareStatement(query);
            res = prepStmt.executeQuery();

            ArrayList<String> messages = new ArrayList<>();
            while (res.next()) {
                if (!(res.getString("input").equals("")) && !(LiveChatComponents.checkWord(res.getString("input")))) {
                    int userId = res.getInt("userID");
                    messages.add(getUsername(userId) + ": " + res.getString("input"));
                }
            }
            UserInfo.setTempHighestChatID(getHighestChatID());
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }

    public static int getHighestChatID() {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        int result = -1;
        try {
            con = HikariCP.getCon();
            String query = "select max(ChatID) from CHAT";
            prepStmt = con.prepareStatement(query);
            res = prepStmt.executeQuery();
            while (res.next()) {
                result = res.getInt("max(ChatID)");
            }
            return result;
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt,res);
        }
        return -1;


    }

    //Livechat methods end

    // Get username given userID
    public static String getUsername(int userId) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT userName FROM USERS WHERE userID =?";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, userId);
            res = prepStmt.executeQuery();
            String output = "";
            while (res.next()) {
                output = res.getString("username");
            }
            return output;
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }

    public static String getUserEmail(int userId) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT userMail FROM USERS WHERE userID=?";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, userId);
            res = prepStmt.executeQuery();
            String output = "";
            if (res.next()) {
                output = res.getString("userMail");
            }
            return output;
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }

    // Gets number of players in a game
    public static int getAmtPlayer(){
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT COUNT(userID) FROM GAME;";
            prepStmt = con.prepareStatement(query);
            res = prepStmt.executeQuery();
            if (res.next()) {
                return res.getInt("COUNT(userID)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return 0;
    }

    // Gets the amount of points user has
    public static int getPoints(){
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT points FROM GAME where userID = ?;";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, UserInfo.getUserID());
            res = prepStmt.executeQuery();
            if (res.next()) {
                return res.getInt("points");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return 0;
    }

    // Updates the amount of points this user has
    public static void updatePoints(int addPoints){
        Connection con = null;
        PreparedStatement prepStmt = null;
        int oldPoints = getPoints();
        int newPoints = oldPoints + addPoints;
        try {
            con = HikariCP.getCon();
            String query = "UPDATE GAME SET points = " + newPoints +"WHERE userID = ?";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, UserInfo.getUserID());
            prepStmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }

    // Fetches avatarID from database, allows game to show the users avatar inGame using UserInfo.avatarID variable
    public static void updateAvatarID(int userID) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT avatarID FROM USERS WHERE userID=?;";
            prepStmt = con.prepareStatement(query);
            prepStmt.setInt(1, userID);
            res = prepStmt.executeQuery();
            if (res.next()) {
                UserInfo.setAvatarID(res.getInt("avatarID"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
    }

    // returns a list of Players (all the people in GAME). A player has ID, name, avatarId and points
    public static ArrayList<Player> getPlayers() {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            con = HikariCP.getCon();
            String query = "SELECT userName, userID, avatarID, points FROM GAME JOIN USERS USING(userID);";
            prepStmt = con.prepareStatement(query);
            res = prepStmt.executeQuery();
            while (res.next()) {
                int userID = res.getInt("userID");
                String userName = res.getString("userName");
                int avatarID = res.getInt("AvatarID");
                double points = res.getDouble("points");
                players.add(new Player(userName, userID, avatarID, points));
            }
            return players;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }

    // Gets the number of people who has guessed correctly
    public static int getAmtCorrect(){
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT SUM(correctGuess) FROM GAME;";
            prepStmt = con.prepareStatement(query);
            res = prepStmt.executeQuery();
            if (res.next()) {
                return res.getInt("SUM(correctGuess)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return 0;
    }

    // Uploads image to database
    public static void uploadImage(byte[] blob, String word) {
        Connection con = null;
        PreparedStatement prepStmt = null;
        try {
            con = HikariCP.getCon();
            String query = "INSERT INTO DRAW VALUES (default, ?, ?, DATE_ADD(NOW(), INTERVAL 140 SECOND));";
            prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, word);
            prepStmt.setBlob(2, new SerialBlob(blob));
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }

    public static void updateImage(byte[] blob){
        Connection con = null;
        PreparedStatement prepStmt = null;
        try{
            con = HikariCP.getCon();
            String query = "UPDATE DRAW SET drawing = ? ORDER BY gameID DESC LIMIT 1;";
            prepStmt = con.prepareStatement(query);
            prepStmt.setBlob(1, new SerialBlob(blob));
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, null);
        }
    }

    public static InputStream getImage(){
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try{
            con = HikariCP.getCon();
            String query = "SELECT drawing FROM DRAW ORDER BY gameID DESC LIMIT 1;";
            prepStmt = con.prepareStatement(query);
            res = prepStmt.executeQuery();
            if (res.next()){
                return res.getBlob("drawing").getBinaryStream();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }

    public static void setRandomWord(){
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try{
            con = HikariCP.getCon();
            String wordQuery = "UPDATE DRAW SET word=(SELECT word FROM LIBRARY ORDER BY rand() LIMIT 1 ) ORDER BY gameiD DESC LIMIT 1";
            prepStmt = con.prepareStatement(wordQuery);
            prepStmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(con, prepStmt, res);
        }
    }

    public static String getRandomWord(){
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        try{
            con = HikariCP.getCon();
            String wordQuery = "SELECT word FROM DRAW ORDER BY gameID DESC LIMIT 1;";
            prepStmt = con.prepareStatement(wordQuery);
            res = prepStmt.executeQuery();
            if(res.next()){
                return res.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }

    public static Date getDrawTimer() {
        Connection con = null;
        PreparedStatement prepStmt = null;
        ResultSet res = null;
        Date date = null;
        try {
            con = HikariCP.getCon();
            String query = "SELECT timer FROM DRAW ORDER BY gameID DESC LIMIT 1";
            prepStmt = con.prepareStatement(query);
            res = prepStmt.executeQuery();
            if (res.next()) {
                Date time = res.getTime("timer");
                Date date1 = res.getDate("timer");
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(date1);
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(time);

                cal1.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
                cal1.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
                cal1.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
                date = cal1.getTime();
            }

            return date;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, prepStmt, res);
        }
        return null;
    }
}
