package Database;

import java.sql.*;

public class DBConnection {

    private static final String brukernavn = "zuimran";
    private static final String passord = "xaXIMlNC"; //xaXIMlNC
    private static String table = null;

    private Connection con;
    private Statement stmt;
    private ResultSet res;
    private ResultSetMetaData rsmd;

    public DBConnection() {
        DBConnect();
    }

    public void registerUser(String userName, String password, String userEmail, int avatarID) {
        try {
            stmt.executeUpdate("INSERT INTO USERS VALUES (default, " + userName + ", " + password + ", " + userEmail + ", " + avatarID + ", 0)");
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean alreadyExistsIn(String columnName, String input) {
        try {
            res = stmt.executeQuery("SELECT * FROM USERS WHERE " + columnName + "=" + input);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res == null) {
            return false;
        }
        return true;
    }


    // Help-method that extracts lists of e.g. usernames or emails
    private String[] getColumnFromUsers(String columnName) {
        int columnCount = 0;
        try {
            res = stmt.executeQuery("SELECT " + columnName + " FROM USERS");
            rsmd = res.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] list = new String[columnCount];
        try {
            if (res != null) {
                while (res.next()) {
                    for (int i = 0; i < columnCount; i++) {
                        try {
                            list[i] = res.getString(columnName);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void DBConnect() {
        String databaseDriver = "com.mysql.jdbc.Driver";
        try{
            Class.forName(databaseDriver);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }


        String databaseName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + brukernavn + "?user=" + brukernavn + "&password=" + passord;

        try {
            con = DriverManager.getConnection(databaseName);
            stmt = con.createStatement();
            res = null;
            rsmd = null;
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
}