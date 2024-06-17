package com.project.certified.services.Postgres;

import com.project.certified.config.jwt.JwtService;
import com.project.certified.dto.auth.AuthResponseDto;
import com.project.certified.dto.auth.LoginRequestDto;
import com.project.certified.dto.auth.RegisterRequestDto;
import com.project.certified.entity.Postgres.UserEntity;
import com.project.certified.repository.Postgres.UserRepositoryPostgres;
import com.project.certified.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServicePostgres {

    private final UserRepositoryPostgres userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthResponseDto login(final LoginRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByEmail(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponseDto.builder()
                .token(token)
                .build();

    }

    public AuthResponseDto register(final RegisterRequestDto request) {
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
