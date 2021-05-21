package realizations.dao.impl;

import models.connections.DBConnection;
import models.Basura;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realizations.dao.interfaces.IBasuraDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasuraDAO implements IBasuraDAO {

    private Connection transactionalConnection;

    private static final Logger LOGGER = LogManager.getLogger(BasuraDAO.class);

    private static final String SQL_INSERT = "INSERT INTO Basura_espacial.Basura (cod_nav, speed, weight, size, coordR, coordFi) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM garbages";
    public BasuraDAO(Connection transactionalConnection){this.transactionalConnection = transactionalConnection;}

    @Override
    public void create(Basura basura) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, basura.getCod());
            stmt.setDouble(2, basura.getVelocity());
            stmt.setDouble(3, basura.getWeight());
            stmt.setDouble(4, basura.getSize());
            stmt.setDouble(5, basura.getCoordR());
            stmt.setDouble(6, basura.getCoordFi());
            stmt.executeUpdate();
            LOGGER.info("Executing query:" + SQL_INSERT);
            LOGGER.info(basura.toString() + "has been added");
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            DBConnection.close(stmt);
            if (this.transactionalConnection == null) {
                DBConnection.close(conn);
            }
        }
    }

    public List<Basura> read() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Basura basura = null;
        List<Basura> basuras = new ArrayList<Basura>();

        try {
            conn = this.transactionalConnection != null ? this.transactionalConnection : DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int cod = rs.getInt("cod_nav");
                double velocity = rs.getDouble("velocity");
                double weight = rs.getDouble("weight");
                double size = rs.getDouble("size");
                double coordR = rs.getDouble("coordR");
                double coordFi = rs.getDouble("coordFi");

                basura = new Basura();
                basura.setCod(cod);
                basura.setVelocity(velocity);
                basura.setWeight(weight);
                basura.setSize(size);
                basuras.add(basura);
                LOGGER.info(basura.toString() + "has been added");
            }
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            if (this.transactionalConnection == null) {
                DBConnection.close(conn);
            }

        }
        return basuras;
    }
}
