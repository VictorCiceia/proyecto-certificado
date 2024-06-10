package com.project.certified.services;

import com.project.certified.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto findById(String id);

    UserDto save(UserDto userDto);

    UserDto update(UserDto userDto, String id);

    void deleteById(String id);

}
