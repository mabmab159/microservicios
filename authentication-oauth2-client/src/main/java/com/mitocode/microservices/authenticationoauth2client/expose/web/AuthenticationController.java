package com.mitocode.microservices.authenticationoauth2client.expose.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class AuthenticationController {
    @GetMapping("/authenticate")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Logueado");
    }

}
