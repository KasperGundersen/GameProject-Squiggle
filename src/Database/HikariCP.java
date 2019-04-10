package Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The connection pool used for the game. HikariCP
 */
public class HikariCP {

    private static HikariDataSource ds;

    /**
     * Configuration of the connection pool
     */
    static{

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://mysql.stud.idi.ntnu.no:3306/zuimran");
        config.setUsername("zuimran");
        config.setPassword("xaXIMlNC");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setPoolName("Squiggle Pool");
        config.setMaxLifetime(35000);
        ds = new HikariDataSource(config);
    }

    /**
     * Gets a connection from the HikariCP connection pool
     * @return Connection to the database
     * @throws SQLException
     */
    public static Connection getCon() throws SQLException {
        return ds.getConnection();
    }
}
