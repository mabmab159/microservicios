package com.mitocode.microservices.clientserviceribbon.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserServiceResponse {
    private List<UserResponse> content;
}
