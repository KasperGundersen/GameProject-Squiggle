package Components;

import Database.DBConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.paint.Color;

/**
 * UserInfo class includes information regarding every single user
 */

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
    private static int tempHighestChatID;
    private static boolean guessedCorrectly = false;


    /**
     * Constructor which is called whenever a new user is registered
     */
    public UserInfo() {
        this.userName = null;
        this.userID = 0;
        this.colorHex = null;
        this.soundOn = true;
        this.avatarID = 0;
        // this.drawing = false;
        this.fontSize = 16;
        this.color = Color.web("0xffe6b3");

        this.userEmail = null;
    }

    /**
     * Gets the username of the user
     * @return the username of the user
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * Gets the userID of the user
     * @return the userID of the user
     */
    public static int getUserID() {
        return userID;
    }

    /**
     * Gets the avatarID of the user
     * @return the avatarID of the user
     */
    public static int getAvatarID() {
        return avatarID;
    }

    /**
     * Gets the boolean-value of drawing player
     * @return true or false depending on the status of the player in-game

    public static boolean getDrawing() {
        return drawing;
    }
    */

    public static String getUserEmail() {
        return userEmail;
    }

    /**
     * Method that runs on login, updates UserInfo.userID variable
     * and fetches avatarID from given user
     * @param userId takes the userID in order to recognize the correct user
     */
    public static void initializeUser(int userId) {
        userID = userId;
        DBConnection.updateAvatarID(userId);
        userEmail = DBConnection.getUserEmail(userId);
    }

    /**
     * Sets the username of the user
     * @param newName new username
     */
    public static void setUserName(String newName) {
        userName = newName;
    }

    /**
     * Sets the drawing-status of the user
     * @param bool true or false depending on the status

    public static void setDrawing(boolean bool) {
        drawing = bool;
    }
    */

    /**
     * Sets the avatarID of the user
     * @param newID the new avatarID of the user
     */

    public static void setAvatarID(int newID) {
        avatarID = newID;
    }

    /**
     * Sets the fontSize of the user
     * @param fontSize new fontsize to the user
     */
    public static void setFontSize(int fontSize) {
        UserInfo.fontSize = fontSize;
    }

    /**
     * gets the fontsize of the user
     * @return the fontsize of the user
     */

    public static int getFontSize() {
        return fontSize;
    }

    /**
     * Gets the color of the user
     * @return the color of the user
     */
    public static Color getColor() {
        return color;
    }

    /**
     * Sets the color of the user
     * @param color new color of the user
     */
    public static void setColor(Color color) {
        UserInfo.color = color;
    }

    /**
     * Gets the tempHighestChatID in the users chat
     * @return the temporary highest chatID
     */

    public static int getTempHighestChatID() {
        return tempHighestChatID;
    }

    /**
     * Sets the temporary highest chatID
     * @param tempHighestChatID new highest chatID
     */
    public static void setTempHighestChatID(int tempHighestChatID) {
        UserInfo.tempHighestChatID = tempHighestChatID;
    }

    public static boolean getGuessedCorrectly() {
        return guessedCorrectly;
    }

    public static void setGuessedCorrectly(boolean guessedCorrectly) {
        UserInfo.guessedCorrectly = guessedCorrectly;
    }
}
