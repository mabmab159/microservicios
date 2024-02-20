package com.mitocode.microservices.authenticationoauth2client.expose.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class AuthenticationController {
    @GetMapping("/authenticate")
    public ResponseEntity<String> login(@AuthenticationPrincipal OAuth2User user) {
        String result = "Error";
        if (user != null) {
            result = "Logueo exitoso. Bienvenido " + user.getAttribute("name");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/resource")
    public ResponseEntity<String> permitAll() {
        return ResponseEntity.ok("Recurso publico");
    }
}
