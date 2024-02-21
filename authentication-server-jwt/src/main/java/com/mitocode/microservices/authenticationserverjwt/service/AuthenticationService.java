package com.mitocode.microservices.authenticationserverjwt.service;

import com.mitocode.microservices.authenticationserverjwt.config.JwtService;
import com.mitocode.microservices.authenticationserverjwt.model.entity.UserEntity;
import com.mitocode.microservices.authenticationserverjwt.model.request.AuthenticationRequest;
import com.mitocode.microservices.authenticationserverjwt.model.request.RegisterRequest;
import com.mitocode.microservices.authenticationserverjwt.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public String authenticate(AuthenticationRequest request) {
        return "TOKEN";
    }

    public String register(RegisterRequest request) {
        UserEntity userEntity = UserEntity.builder()
                .name(request.name())
                .username(request.username())
                .lastname(request.lastname())
                .email(request.email())
                .roles(request.roles())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepository.save(userEntity);
        return jwtService.generateToken(userEntity);
    }
}
