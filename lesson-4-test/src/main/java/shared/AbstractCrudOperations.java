package shared;

import exception.AlreadyExistException;
import exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface AbstractCrudOperations<T> {

    List<T> readAll() throws SQLException;

    T read(int id) throws SQLException, NotFoundException;

    void create(T t) throws SQLException, AlreadyExistException;

    void delete(int id) throws SQLException, NotFoundException;

}
