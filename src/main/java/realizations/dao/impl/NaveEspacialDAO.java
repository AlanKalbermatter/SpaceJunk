package realizations.dao.impl;

import models.connections.DBConnection;
import models.NaveEspacial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realizations.dao.interfaces.INaveEspacialDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NaveEspacialDAO implements INaveEspacialDAO {
    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(BasuraDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Space_ships(name, mission, crew_member) VALUES(?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM Space_ships";

    public NaveEspacialDAO(Connection transactionalConnection){ this.transactionalConnection = transactionalConnection;}

    @Override
    public void create(NaveEspacial naveEspacial) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, naveEspacial.getCod());
            stmt.setString(2, naveEspacial.getMission());

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

    public List<NaveEspacial> read() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        NaveEspacial naveEspacial = null;
        List<NaveEspacial> naveEspacials = new ArrayList<NaveEspacial>();

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String mission = rs.getString("mission");
                long crewMember = rs.getLong("crew_member");

                naveEspacial = new NaveEspacial();
                naveEspacial.setMission(mission);
                LOGGER.info(naveEspacial.toString() + "has been added");
            }
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if (this.transactionalConnection == null) {
                DBConnection.close(conn);
            }

        }
        return naveEspacials;
    }
}
