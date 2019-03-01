/* This class has static methods so we wont have to make objects of this class to use its methods */
package Database;

import java.sql.*;

public class DBConnection {

    // All information needed to connect to the database
    private static final String username = "zuimran";
    private static final String password = "xaXIMlNC"; //xaXIMlNC
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
            stmt = con.createStatement();
            res = stmt.executeQuery("SELECT " + columnName + " FROM USERS WHERE " + columnName + "=\"" + input + "\";");
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
            stmt = con.createStatement();
            stmt.executeUpdate("UPDATE USERS SET loggedIn=" + loggedIn + " WHERE userName=\"" + username + "\";");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}