package realizations.dao.impl;

import models.Agencia;
import models.Orbita;
import models.connections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgenciaDAO {

    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(AgenciaDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Basura_espacial.Agencia (nombre, numero_personas) VALUES(?, ?)";

    public AgenciaDAO(Connection transactionalConnection) {this.transactionalConnection = transactionalConnection;}

    public void create(Agencia agencia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, agencia.getNombre());
            stmt.setDouble(2, agencia.getNumeroPersonas());
            stmt.executeUpdate();
            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(agencia.toString() + "has been added");
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
