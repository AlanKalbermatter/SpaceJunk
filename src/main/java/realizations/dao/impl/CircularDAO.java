package realizations.dao.impl;

import models.Circular;
import models.Eliptica;
import models.connections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CircularDAO {

    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(CircularDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Basura_espacial.Circular (id, orbita_id, geoestacionaria) VALUES(?, ?, ?)";

    public CircularDAO(Connection transactionalConnection) {this.transactionalConnection = transactionalConnection;}


    public void create(Circular circular) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, circular.getId());
            stmt.setInt(2, circular.getOrbitaId());
            stmt.setInt(3, circular.getGeoestacionaria());
            stmt.executeUpdate();

            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(circular.toString() + "has been added");
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
