import com.github.javafaker.Faker;
import models.DBConnection;
import models.SpaceShip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realizations.dao.impl.GarbageDAO;
import realizations.dao.impl.SpaceShipDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Connection conn = null;
        Faker faker = new Faker();
        try {
            conn = DBConnection.getConnection();
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            SpaceShipDAO spaceShipDAO = new SpaceShipDAO(conn);
            SpaceShip spaceShip = new SpaceShip();
            String name = faker.space().nasaSpaceCraft();
            spaceShip.setName(name);
            System.out.println(name);
            LOGGER.info(spaceShip.getName());
            List<SpaceShip> spaceShips = new ArrayList<SpaceShip>();

            conn.commit();
            LOGGER.debug("Transaction has been done");
        } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
                LOGGER.warn("rollback");
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
       }
    }
}
