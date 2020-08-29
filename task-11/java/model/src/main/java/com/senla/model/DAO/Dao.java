package main.java.com.senla.model.DAO;

import java.sql.SQLException;
import java.util.List;


public interface Dao<Entity, Key> {
    boolean create (Entity model) throws SQLException;
    boolean update (Entity model);
    boolean delete (Entity model);
    Entity read(Key id);
    List<Entity> getAll();

}
