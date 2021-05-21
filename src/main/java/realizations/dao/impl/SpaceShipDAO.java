package realizations.dao.impl;

import models.Garbage;
import models.SpaceShip;
import realizations.dao.interfaces.ISpaceShipDAO;

import java.sql.SQLException;
import java.util.List;

public class SpaceShipDAO implements ISpaceShipDAO {
    @Override
    public void create(SpaceShip entity) {

    }

    @Override
    public List<Garbage> read() throws SQLException {
        return null;
    }
}
