package com.project.certified.repository.Mongo;

import com.project.certified.entity.Mongo.LoanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepositoryMongo extends MongoRepository<LoanEntity, String> {

    List<LoanEntity> findAllByBook(String id);

    List<LoanEntity> findAllByUser(String id);

}