package com.mitocode.microservices.userservice.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
@Data //=> @Getter, @Setter, @EqualsAndHashCode, @ToString, @Value
@Builder
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String[] roles;
}
