package com.project.certified.repository.Postgres;

import com.project.certified.entity.Postgres.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryPostgres extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

}
