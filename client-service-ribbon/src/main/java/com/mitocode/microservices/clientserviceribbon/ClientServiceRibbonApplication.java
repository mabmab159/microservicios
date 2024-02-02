package com.mitocode.microservices.clientserviceribbon;

import com.mitocode.microservices.clientserviceribbon.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@RibbonClient(name = "user-service-mitocode", configuration = RibbonConfig.class) //El nombre no puede ser el mismo que el id
@SpringBootApplication
public class ClientServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceRibbonApplication.class, args);
    }

}
