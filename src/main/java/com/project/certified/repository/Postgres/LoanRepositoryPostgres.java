package com.project.certified.repository.Postgres;

import com.project.certified.entity.Postgres.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepositoryPostgres extends JpaRepository<LoanEntity, String> {

    List<LoanEntity> findAllByBookId(String id);

    List<LoanEntity> findAllByUserId(String id);

}
