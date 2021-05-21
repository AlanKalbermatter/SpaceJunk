package realizations.dao.impl;

import models.CrewMember;
import models.DBConnection;
import models.Garbage;
import models.SpaceShip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realizations.dao.interfaces.ISpaceShipDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpaceShipDAO implements ISpaceShipDAO {
    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(GarbageDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Space_ships(name, mission, crew_member) VALUES(?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM Space_ships";

    public SpaceShipDAO(Connection transactionalConnection){ this.transactionalConnection = transactionalConnection;}

    @Override
    public void create(SpaceShip spaceShip) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, spaceShip.getName());
            stmt.setString(2, spaceShip.getMission());
            stmt.setObject(3, spaceShip.getCrewMember());

            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(spaceShip.toString() + "has been added");
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBConnection.close(stmt);
            if (this.transactionalConnection == null) {
                DBConnection.close(conn);
            }
        }
    }

    public List<SpaceShip> read() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SpaceShip spaceShip = null;
        List<SpaceShip> spaceShips = new ArrayList<SpaceShip>();

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String mission = rs.getString("mission");
                long crewMember = rs.getLong("crew_member");

                spaceShip = new SpaceShip();
                spaceShip.setId(id);
                spaceShip.setName(name);
                spaceShip.setMission(mission);
                LOGGER.info(spaceShip.toString() + "has been added");
            }
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if (this.transactionalConnection == null) {
                DBConnection.close(conn);
            }

        }
        return spaceShips;
    }
}
