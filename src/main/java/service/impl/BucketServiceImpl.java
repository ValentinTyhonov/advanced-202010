package service.impl;

import dao.BucketDao;
import dao.impl.BucketDaoImpl;
import exception.NotFoundException;
import lombok.extern.log4j.Log4j;
import model.Bucket;
import service.BucketService;

import java.sql.SQLException;
@Log4j
public class BucketServiceImpl implements BucketService {

    private BucketDao bucketDao;

    public BucketServiceImpl(){
        this.bucketDao = new BucketDaoImpl();
    }
    @Override
    public Bucket read(int id) throws SQLException, NotFoundException {

        log.info("Trying to get bucket");
        Bucket bucket = bucketDao.read(id);
        if (bucket == null){
            throw new NotFoundException("Bucket with id " + id + " doesn't exist.");
        }
        return bucket;
    }

    @Override
    public void create(Bucket bucket) throws SQLException {

        log.info("Trying to create bucket");

        bucketDao.create(bucket);
        log.info("New bucket with id " + bucket.getId() + "was create.");
    }


    @Override
    public void delete(int id) throws SQLException, NotFoundException {

        if(bucketDao.delete(id)){
            throw new NotFoundException("User with id " + id + "doesn't exist");
        }
    }
}
