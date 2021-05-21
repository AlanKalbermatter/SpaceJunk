package realizations.dao.interfaces;

import models.SpaceShip;

import java.sql.SQLException;
import java.util.List;

public interface IEntityDAO<E> {
    void create(E entity);
}
