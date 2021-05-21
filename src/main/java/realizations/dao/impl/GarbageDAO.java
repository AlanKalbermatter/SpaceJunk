package realizations.dao.impl;

import models.DBConnection;
import models.Garbage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realizations.dao.interfaces.IGarbageDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GarbageDAO implements IGarbageDAO{

    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(GarbageDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Garbages(last_orbit, velocity, weight, size) VALUES(?, ?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM garbages";
    public GarbageDAO(){}

    @Override
    public void create(Garbage garbage) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, garbage.getLastOrbit());
            stmt.setDouble(2, garbage.getVelocity());
            stmt.setDouble(3, garbage.getWeight());
            stmt.setDouble(4, garbage.getSize());

            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(garbage.toString() + "has been added");
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBConnection.close(stmt);
            if (this.transactionalConnection == null) {
                DBConnection.close(conn);
            }
        }
    }

    @Override
    public List<Garbage> read() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Garbage garbage = null;
        List<Garbage> garbages = new ArrayList<Garbage>();

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String lastOrbit = rs.getString("last_orbit");
                double velocity = rs.getDouble("velocity");
                double weight = rs.getDouble("weight");
                double size = rs.getDouble("size");

                garbage = new Garbage();
                garbage.setId(id);
                garbage.setLastOrbit(lastOrbit);
                garbage.setVelocity(velocity);
                garbage.setWeight(weight);
                garbage.setSize(size);
                garbages.add(garbage);
                LOGGER.info(garbage.toString() + "has been added");
            }
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if (this.transactionalConnection == null) {
                DBConnection.close(conn);
            }

        }
        return garbages;
    }
}
