package com.mitocode.microservices.clientservice.model.request;

import lombok.NonNull;

import java.util.Objects;

public record UserResponseRecord(String id, @NonNull String customeName /*Decorador para no permitir nulos*/,
                                 String last, String username, /*int edad, */String email, String password,
                                 String[] roles) {

    public UserResponseRecord {
        //Se pueden agregar validaciones en el constructor del record
        /*if (edad < 10)
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
         */
    }
}
