package com.mitocode.microservices.userservice.repository;

import com.mitocode.microservices.userservice.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "user")
public interface UserRepository extends CrudRepository<UserEntity, String> {
    @RestResource(exported = false) //No expondra esta operacion
    void deleteById(String id);

    // @RestResource(path = "correo")
    // List<UserEntity> getAllByEmail(String email);

    @RestResource(path = "correo") // /users/search/correo?email=hoy@g
    List<UserEntity> getAllByEmailContaining(String email);
}
