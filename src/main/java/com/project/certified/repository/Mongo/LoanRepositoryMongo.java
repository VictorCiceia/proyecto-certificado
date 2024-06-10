package com.project.certified.repository.Mongo;

import com.project.certified.entity.Mongo.LoanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepositoryMongo extends MongoRepository<LoanEntity, String> {

}