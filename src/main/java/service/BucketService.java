package service;

import exception.NotFoundException;
import model.Bucket;

import java.sql.SQLException;

public interface BucketService {

    Bucket read(int id) throws SQLException, NotFoundException;

    void create(Bucket bucket) throws SQLException;

    void delete(int id) throws SQLException, NotFoundException;
}
