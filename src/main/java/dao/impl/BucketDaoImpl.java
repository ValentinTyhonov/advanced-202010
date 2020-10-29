package dao.impl;

import dao.BucketDao;
import model.Bucket;
import util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket read(int id) throws SQLException {
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id,purchase_date FROM bucket WHERE id=?")
                ){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            Bucket bucket = new Bucket();
            while (resultSet.next()){
                bucket.setId(resultSet.getInt("id"));
                bucket.setPurchaseDate(resultSet.getTimestamp("purchase_date"));
            }

        }
        return null;
    }

    @Override
    public void create(Bucket bucket) throws SQLException {
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO bucket(purchase_date) VALUES(?)"
                )
                ){
            statement.setTimestamp(1, bucket.getPurchaseDate());
            statement.execute();
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM bucket WHERE id=?"
                )
                ){
            statement.setInt(1, id);
            return statement.executeUpdate()>0;
        }
    }
}
