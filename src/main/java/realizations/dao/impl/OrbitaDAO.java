package realizations.dao.impl;

import models.connections.DBConnection;
import models.Orbita;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realizations.dao.interfaces.IOrbitaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrbitaDAO {

    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(OrbitaDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Basura_espacial.Orbita (coordFi, coordR) VALUES(?, ?)";

    public OrbitaDAO(Connection transactionalConnection) {this.transactionalConnection = transactionalConnection;}


    public void create(Orbita orbita) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setDouble(1, orbita.getCoordR());
            stmt.setDouble(2, orbita.getCoordFi());
            stmt.executeUpdate();

            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(orbita.toString() + "has been added");
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
