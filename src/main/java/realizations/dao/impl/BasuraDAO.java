package realizations.dao.impl;

import models.connections.DBConnection;
import models.Basura;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realizations.dao.interfaces.IBasuraDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasuraDAO implements IBasuraDAO {

    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(BasuraDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Basura_espacial.Basura (cod_nav, speed, weight, size, orbita_id) VALUES(?, ?, ?, ?, ?)";
    public BasuraDAO(Connection transactionalConnection){this.transactionalConnection = transactionalConnection;}


    public void create(Basura basura) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, basura.getCod());
            stmt.setDouble(2, basura.getVelocity());
            stmt.setDouble(3, basura.getWeight());
            stmt.setDouble(4, basura.getSize());
            stmt.setInt(5, basura.getOrbitaId());
            stmt.executeUpdate();

            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(basura.toString() + "has been added");
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBConnection.close(stmt);
            if (this.transactionalConnection == null) {
                DBConnection.close(conn);
            }
        }
    }
}
