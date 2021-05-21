package realizations.dao.interfaces;

import models.Garbage;

import java.sql.SQLException;
import java.util.List;

public interface IEntityDAO<E> {
    void create(E entity);
    List<Garbage> read() throws SQLException;
}
