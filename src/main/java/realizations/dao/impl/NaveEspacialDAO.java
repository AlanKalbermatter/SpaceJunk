package realizations.dao.impl;

import models.connections.DBConnection;
import models.NaveEspacial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realizations.dao.interfaces.INaveEspacialDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NaveEspacialDAO implements INaveEspacialDAO {
    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(NaveEspacialDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Basura_espacial.Nave (cod, matricula, mision, agencia_name) VALUES(?, ?, ?, ?)";

    public NaveEspacialDAO(Connection transactionalConnection){ this.transactionalConnection = transactionalConnection;}

    public void create(NaveEspacial naveEspacial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, naveEspacial.getTipoNave());
            stmt.setInt(2, naveEspacial.getMatricula());
            stmt.setString(3, naveEspacial.getMision());
            stmt.setString(4, naveEspacial.getAgencia());

            stmt.executeUpdate();

            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(naveEspacial.toString() + "has been added");
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
