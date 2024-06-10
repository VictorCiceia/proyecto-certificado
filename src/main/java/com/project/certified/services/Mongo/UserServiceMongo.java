package com.project.certified.services.Mongo;

import com.project.certified.dto.UserDto;
import com.project.certified.entity.Mongo.UserEntity;
import com.project.certified.exception.ResourceNotFoundException;
import com.project.certified.repository.Mongo.UserRepositoryMongo;
import com.project.certified.services.UserService;
import com.project.certified.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceMongo implements UserService {

    @Autowired
    private UserRepositoryMongo userRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(final String id) {
        return userRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public UserDto update(final UserDto userDto, String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        UserEntity updatedUser = userRepository.save(user);
        return mapper.toDto(updatedUser);
    }

    @Override
    public void deleteById(final String id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepository.deleteById(id);
    }
}
