package com.project.certified.repository.Mongo;


import com.project.certified.entity.Mongo.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryMongo extends MongoRepository<BookEntity, String> {

}