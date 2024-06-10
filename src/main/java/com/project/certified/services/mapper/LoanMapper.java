package com.project.certified.services.mapper;

import com.project.certified.dto.LoanDto;
import com.project.certified.entity.Postgres.BookEntity;
import com.project.certified.entity.Postgres.LoanEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanDto toDto(LoanEntity entity);

    LoanDto toDto(com.project.certified.entity.Mongo.LoanEntity entity);

    BookEntity toEntity(LoanDto dto);

    com.project.certified.entity.Mongo.LoanEntity toEntityMongo(LoanDto dto);

}
