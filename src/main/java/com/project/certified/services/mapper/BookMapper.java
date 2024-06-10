package com.project.certified.services.mapper;

import com.project.certified.dto.BookDto;
import com.project.certified.entity.Postgres.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(BookEntity entity);

    BookDto toDto(com.project.certified.entity.Mongo.BookEntity entity);

    BookEntity toEntityPostgre(BookDto dto);

    com.project.certified.entity.Mongo.BookEntity toEntityMongo(BookDto dto);

}
