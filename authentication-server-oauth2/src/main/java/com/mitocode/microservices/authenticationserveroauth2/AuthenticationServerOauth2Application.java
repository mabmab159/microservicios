package com.mitocode.microservices.authenticationserveroauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class AuthenticationServerOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServerOauth2Application.class, args);
    }


}
