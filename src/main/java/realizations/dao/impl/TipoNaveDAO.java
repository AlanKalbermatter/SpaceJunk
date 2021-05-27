package realizations.dao.impl;

import models.connections.DBConnection;
import models.TipoNave;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TipoNaveDAO {
    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(BasuraDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Basura_espacial.Tipo_De_Nave (cod) VALUES(?)";
    public TipoNaveDAO(Connection transactionalConnection){this.transactionalConnection = transactionalConnection;}

    public void create(TipoNave tNave) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, tNave.getCod());
            stmt.executeUpdate();

            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(tNave.toString() + "has been added");
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
