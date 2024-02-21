package com.mitocode.microservices.authenticationoauth2clientkeycloak.expose.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class KeycloakController {

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
