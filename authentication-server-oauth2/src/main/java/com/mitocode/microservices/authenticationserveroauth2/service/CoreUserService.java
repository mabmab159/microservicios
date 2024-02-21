package com.mitocode.microservices.authenticationserveroauth2.service;

import com.mitocode.microservices.authenticationserveroauth2.model.entity.core.UserCore;
import com.mitocode.microservices.authenticationserveroauth2.model.entity.dto.UserDTO;
import com.mitocode.microservices.authenticationserveroauth2.service.repository.UserRepository;
import com.mitocode.microservices.commonmodels.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDTO findUserByUsername(String username) {

        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            log.error("Username: " + username + " no se encuentra en BD.");
        }

        return UserDTO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .lastname(userEntity.getLastname())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .password(userEntity.getPassword())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = findUserByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("Username: " + username + " no se encuentra registrado");
        }
        List<GrantedAuthority> grantedAuthorities = Arrays.stream(userDTO.getRoles())
                .map(SimpleGrantedAuthority::new)
                .peek(simpleGrantedAuthority -> log.info("[CoreUserService][" + username + "] - Rol: " + simpleGrantedAuthority.getAuthority()))
                .collect(Collectors.toList());
        userDTO.setGrantedAuthorities(grantedAuthorities);
        return new UserCore(userDTO);
    }
}
