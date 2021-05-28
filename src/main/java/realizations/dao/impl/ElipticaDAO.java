package realizations.dao.impl;

import models.Eliptica;
import models.Orbita;
import models.connections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElipticaDAO {

    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(ElipticaDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Basura_espacial.Eliptica (id, orbita_id, excentricidad) VALUES(?, ?, ?)";

    public ElipticaDAO(Connection transactionalConnection) {this.transactionalConnection = transactionalConnection;}


    public void create(Eliptica eliptica) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, eliptica.getId());
            stmt.setInt(2, eliptica.getOrbitaId());
            stmt.setInt(3, eliptica.getExentricidad());
            stmt.executeUpdate();

            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(eliptica.toString() + "has been added");
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
