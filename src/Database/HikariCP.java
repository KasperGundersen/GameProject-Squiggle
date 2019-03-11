package Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCP {
    private static HikariDataSource ds;
    static{

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://mysql.stud.idi.ntnu.no:3306/zuimran");
        config.setUsername("zuimran");
        config.setPassword("xaXIMlNC");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    public static Connection getCon() throws SQLException {
        return ds.getConnection();
    }
}
