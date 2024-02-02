package com.mitocode.microservices.clientservice.proxy.openfeign;

import com.mitocode.microservices.clientservice.model.request.UserServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface UserServiceFeign {
    @GetMapping("/mitocode/user")
    UserServiceResponse getAllUser();
}
