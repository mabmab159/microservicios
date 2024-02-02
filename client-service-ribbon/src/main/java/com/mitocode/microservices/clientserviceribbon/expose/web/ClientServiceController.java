package com.mitocode.microservices.clientserviceribbon.expose.web;

import com.mitocode.microservices.clientserviceribbon.model.request.UserResponse;
import com.mitocode.microservices.clientserviceribbon.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientServiceController {
    private final ClientService clientService;

    @GetMapping("/user")
    public List<UserResponse> getAllUser() {
        return clientService.getAllUsers();
    }
}
