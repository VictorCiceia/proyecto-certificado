package com.project.certified.services.mapper;

import com.project.certified.dto.UserDto;
import com.project.certified.entity.Postgres.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDto toDto(UserEntity entity);

    UserDto toDto(com.project.certified.entity.Mongo.UserEntity entity);

    UserEntity toEntity(UserDto dto);

    com.project.certified.entity.Mongo.UserEntity toEntityMongo(UserDto dto);

}
