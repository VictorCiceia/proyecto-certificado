package com.project.certified.services.Postgres;

import com.project.certified.dto.UserDto;
import com.project.certified.entity.Postgres.UserEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Postgres.UserRepositoryPostgres;
import com.project.certified.services.UserService;
import com.project.certified.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicePostgres implements UserService {

    @Autowired
    private UserRepositoryPostgres userRepositoryPostgres;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepositoryPostgres.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(final String id) {
        return userRepositoryPostgres.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public UserDto update(final UserDto userDto, String id) {
        UserEntity user = userRepositoryPostgres.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        UserEntity updatedUser = userRepositoryPostgres.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteById(final String id) {
        if (!userRepositoryPostgres.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepositoryPostgres.deleteById(id);
    }
}