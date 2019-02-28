package Components;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class Registration {

    public static void registerUser(Connection conn, String userName, String password, String userEmail, int avatarID) {
        try {
            Statement stmtt = conn.createStatement();
            stmtt.executeUpdate("INSERT INTO USERS VALUES (0, \"" + userName + "\", \"" + password + "\", \"" + userEmail + "\", " + avatarID + ", 0)");
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
