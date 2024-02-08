package com.mitocode.microservices.clientservice.expose.web;

import com.mitocode.microservices.clientservice.model.request.UserResponse;
import com.mitocode.microservices.clientservice.model.request.UserResponseRecord;
import com.mitocode.microservices.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    /*@GetMapping("/user")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return ResponseEntity.ok(clientService.getAllUser());
    }*/

    //Implementacion para records
    @GetMapping("/user")
    public ResponseEntity<List<UserResponseRecord>> getAllUser() {
        return ResponseEntity.ok(clientService.getAllUser());
    }
}
