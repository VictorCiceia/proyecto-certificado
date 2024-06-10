package com.project.certified.repository.Postgres;

import com.project.certified.entity.Postgres.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryPostgres extends JpaRepository<BookEntity, Long> {

}
