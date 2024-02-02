package com.mitocode.microservices.clientservice.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserServiceResponse {
    private List<UserResponse> content;
}
