package com.project.certified.controller;

import com.project.certified.config.DataBase;
import com.project.certified.dto.auth.AuthResponseDto;
import com.project.certified.dto.auth.LoginRequestDto;
import com.project.certified.dto.auth.RegisterRequestDto;
import com.project.certified.services.Mongo.AuthServiceMongo;
import com.project.certified.services.Postgres.AuthServicePostgres;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServicePostgres authServicePostgres;

    private final AuthServiceMongo authServiceMongo;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        final DataBase dataBase = DataBase.getInstance();
        if (dataBase.getDatabase().equals(DataBase.MONGO)) {
            return ResponseEntity.ok(authServiceMongo.login(request));
        } else {
            return ResponseEntity.ok(authServicePostgres.login(request));
        }
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {
        final DataBase dataBase = DataBase.getInstance();
        if (dataBase.getDatabase().equals(DataBase.MONGO)) {
            return ResponseEntity.ok(authServiceMongo.register(request));
        } else {
            return ResponseEntity.ok(authServicePostgres.register(request));
        }
    }
}