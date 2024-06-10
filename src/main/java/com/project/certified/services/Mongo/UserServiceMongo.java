package com.project.certified.services.Mongo;

import com.project.certified.dto.UserDto;
import com.project.certified.entity.Mongo.UserEntity;
import com.project.certified.repository.Mongo.UserRepositoryMongo;
import com.project.certified.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMongo implements UserService {

    @Autowired
    private UserRepositoryMongo userRepository;

    @Override
    public List<UserDto> findAll() {
        return List.of();
    }

    @Override
    public UserDto findById(String id) {
        return null;
    }

    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto, String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}