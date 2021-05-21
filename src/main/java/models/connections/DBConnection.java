package models.connections;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private static final Logger LOGGER = LogManager.getLogger(DBConnection.class);

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Basura_espacial?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "root";
    private static BasicDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null){
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASS);
            dataSource.setInitialSize(50);
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void close(ResultSet rs){
        try{
            rs.close();
        }catch(SQLException ex){
            LOGGER.error(ex.getMessage());
        }
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}