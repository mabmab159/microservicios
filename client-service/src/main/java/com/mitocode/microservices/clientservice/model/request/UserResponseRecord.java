package com.mitocode.microservices.clientservice.model.request;

import java.util.Objects;

public record UserResponseRecord(
        String id, String name, String lastname, String username, String email, String password, String[] roles
) {

    public UserResponseRecord {
        Objects.nonNull(name);//No permite crear el objeto si queda pendiente el name
        Objects.nonNull(lastname);
    }
}
