package com.mitocode.microservices.clientservice.service;

import com.mitocode.microservices.clientservice.model.request.UserResponse;
import com.mitocode.microservices.clientservice.model.request.UserResponseRecord;
import com.mitocode.microservices.clientservice.proxy.openfeign.UserServiceFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final UserServiceFeign userServiceFeign;
    private final CircuitBreakerFactory circuitBreakerFactory;

    /*public List<UserResponse> getAllUser() {
        return userServiceFeign.getAllUser().getContent();
    }*/

    //Implementacion para record
    public List<UserResponseRecord> getAllUser() {

        //List<UserResponse> userList = userServiceFeign.getAllUser().getContent();


        //Implementacion para el circuit breaker
        List<UserResponse> userList = circuitBreakerFactory.create("mitocode")
                .run(() -> userServiceFeign.getAllUser().getContent(), this::fallbackMethod);


        List<UserResponseRecord> recordList = new ArrayList<>();
        userList.forEach(p -> {
            recordList.add(new UserResponseRecord(p.getId(), p.getName(), p.getLastname(), p.getUsername(), /*11, */p.getEmail(), p.getPassword(), p.getRoles()));
        });
        recordList.forEach(p -> log.info("Record: " + p.id()));
        return recordList;
    }

    public List<UserResponse> fallbackMethod(Throwable e) {
        log.error("[Mitocode][Error]: "+e.getMessage());
        List<UserResponse> lstUserResponse = new ArrayList<>();
        lstUserResponse.add(UserResponse.builder()
                .roles(new String[]{"ROLE_ADMIN"})
                .id("MYID").name("FAKE_NAME")
                .lastname("FAKE_LASTNAME")
                .password("FAKE_PASS")
                .username("FAKE_USERNAME")
                .email("miguelberrio@mail.com")
                .build());
        return lstUserResponse;
    }


}
