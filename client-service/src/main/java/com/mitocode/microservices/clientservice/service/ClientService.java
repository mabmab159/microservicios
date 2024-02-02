package com.mitocode.microservices.clientservice.service;

import com.mitocode.microservices.clientservice.model.request.UserResponse;
import com.mitocode.microservices.clientservice.proxy.openfeign.UserServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService{
    private final UserServiceFeign userServiceFeign;

    public List<UserResponse> getAllUser() {
        System.out.println(userServiceFeign.getAllUser().getContent());
        return userServiceFeign.getAllUser().getContent();
    }
}
