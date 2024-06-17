package com.project.certified.services.mapper;

import com.project.certified.dto.UserDto;
import com.project.certified.entity.Postgres.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "password", ignore = true)
    UserDto toDto(UserEntity entity);

    @Mapping(target = "password", ignore = true)
    UserDto toDto(com.project.certified.entity.Mongo.UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserEntity toEntityPostgre(UserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    com.project.certified.entity.Mongo.UserEntity toEntityMongo(UserDto dto);

}
