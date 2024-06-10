package com.project.certified.repository.Mongo;

import com.project.certified.entity.Mongo.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryMongo extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

}