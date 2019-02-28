package Database;

import java.sql.*;

public class DBConnection {

    private static final String username = "zuimran";
    private static final String password = "xaXIMlNC"; //xaXIMlNC

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String dBUrl = "jdbc:mysql://mysql.stud.idi.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;

    private Connection con;
    private Statement stmt;
    private ResultSet res;
    private ResultSetMetaData rsmd;

    public DBConnection() {
        con = null;
        stmt = null;
    }

    public Connection getCon() {
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



    public void registerUser(Connection conn, String userName, String password, String userEmail, int avatarID) {
        try {
            Statement stmtt = conn.createStatement();
            stmtt.executeUpdate("INSERT INTO USERS VALUES (default, \"" + userName + "\", \"" + password + "\", \"" + userEmail + "\", " + avatarID + ", 0)");
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean alreadyExistsIn(String columnName, String input) {
        try{
            String databaseDriver = "com.mysql.cj.jdbc.Driver";
            Class.forName(databaseDriver);
            res = stmt.executeQuery("SELECT " + columnName + " FROM USERS WHERE " + columnName + "='" + input +"'");
            while (res.next()) {
                if (res.getString("username").equals(input)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            return false;
        }
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
        try{
            String databaseDriver = "com.mysql.cj.jdbc.Driver";
            Class.forName(databaseDriver);
            con = DriverManager.getConnection(dBUrl);
            stmt = con.createStatement();
            res = null;
            rsmd = null;
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}