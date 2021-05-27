package realizations.dao.impl;

import models.Privada;

import models.connections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PrivadaDAO {
    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(PrivadaDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Basura_espacial.Privada (clave_privada, clave_publica) VALUES(?, ?)";
    public PrivadaDAO(Connection transactionalConnection){this.transactionalConnection = transactionalConnection;}

    public void create(Privada privada) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, privada.getNombre());
            stmt.setString(2, privada.getEsFiscalizadaPor());
            stmt.executeUpdate();

            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(privada.toString() + "has been added");
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
