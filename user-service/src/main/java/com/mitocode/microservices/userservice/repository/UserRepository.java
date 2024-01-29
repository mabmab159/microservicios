package com.mitocode.microservices.userservice.repository;

import com.mitocode.microservices.userservice.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user")
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
