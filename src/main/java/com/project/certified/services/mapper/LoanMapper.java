package com.project.certified.services.mapper;

import com.project.certified.dto.LoanDto;
import com.project.certified.entity.Postgres.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    LoanDto toDto(LoanEntity entity);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    LoanDto toDto(com.project.certified.entity.Mongo.LoanEntity entity);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    LoanEntity toEntityPostgre(LoanDto dto);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    com.project.certified.entity.Mongo.LoanEntity toEntityMongo(LoanDto dto);

}
