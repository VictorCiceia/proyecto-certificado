package com.project.certified.config;


import com.project.certified.repository.Mongo.UserRepositoryMongo;
import com.project.certified.repository.Postgres.UserRepositoryPostgres;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Autowired
    private final UserRepositoryPostgres userRepositoryPostgres;

    @Autowired
    private final UserRepositoryMongo userRepositoryMongo;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        final DataBase dataBase = DataBase.getInstance();
        if (dataBase.getDatabase().equals(DataBase.MONGO)) {
            return username -> this.userRepositoryMongo.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not fournd"));
        } else {
            return username -> this.userRepositoryPostgres.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not fournd"));
        }
    }

}