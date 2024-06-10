package com.project.certified.controller;

import com.project.certified.dto.UserDto;
import com.project.certified.services.UserService;
import com.project.certified.services.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserServiceFactory authServiceFactory) {
        this.userService = authServiceFactory.getService();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") final String id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") final String id, @RequestBody final UserDto userDto) {
        return ResponseEntity.ok(this.userService.update(userDto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") final String id) {
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
