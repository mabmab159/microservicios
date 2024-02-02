package com.mitocode.microservices.clientserviceribbon.service;

import com.mitocode.microservices.clientserviceribbon.model.request.UserResponse;
import com.mitocode.microservices.clientserviceribbon.model.request.UserServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final RestTemplate restTemplate;

    public List<UserResponse> getAllUsers() {
        UserServiceResponse response = restTemplate.getForObject("http://user-service/mitocode/user", UserServiceResponse.class);
        return response.getContent();
    }
}
