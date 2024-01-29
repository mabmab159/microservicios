package com.mitocode.microservices.userservice.model.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
@Data //=> @Getter, @Setter, @EqualsAndHashCode, @ToString, @Value
@Builder
@JsonPropertyOrder({"name", "lastname", "roles"})
public class UserEntity {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String lastname;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String[] roles;
}
