package com.project.certified.services.Mongo;

import com.project.certified.config.jwt.JwtService;
import com.project.certified.dto.auth.AuthResponseDto;
import com.project.certified.dto.auth.LoginRequestDto;
import com.project.certified.dto.auth.RegisterRequestDto;
import com.project.certified.entity.Mongo.UserEntity;
import com.project.certified.repository.Mongo.UserRepositoryMongo;
import com.project.certified.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceMongo {

    private final UserRepositoryMongo userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByEmail(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponseDto.builder()
                .token(token)
                .build();

    }

    public AuthResponseDto register(RegisterRequestDto request) {
        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .lastName(request.getLastname())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponseDto.builder()
                .token(jwtService.getToken(user))
                .build();

    }


}
