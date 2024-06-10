package com.project.certified.repository.Postgres;

import com.project.certified.entity.Postgres.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepositoryPostgres extends JpaRepository<LoanEntity, Long> {

}
