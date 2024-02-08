package com.mitocode.microservices.clientservice.service;

import com.mitocode.microservices.clientservice.model.request.UserResponse;
import com.mitocode.microservices.clientservice.model.request.UserResponseRecord;
import com.mitocode.microservices.clientservice.proxy.openfeign.UserServiceFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final UserServiceFeign userServiceFeign;

    /*public List<UserResponse> getAllUser() {
        return userServiceFeign.getAllUser().getContent();
    }*/

    //Implementacion para record
    public List<UserResponseRecord> getAllUser() {
        List<UserResponse> records = userServiceFeign.getAllUser().getContent();
        List<UserResponseRecord> recordList = new ArrayList<>();
        records.forEach(p -> {
            recordList.add(new UserResponseRecord(p.getId(), null, p.getLastname(), p.getUsername(), /*11, */p.getEmail(), p.getPassword(), p.getRoles()));
        });
        recordList.forEach(p -> log.info("Record: " + p.id()));
        return recordList;
    }


}
