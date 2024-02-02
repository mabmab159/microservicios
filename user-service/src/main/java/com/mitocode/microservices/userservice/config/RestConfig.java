package com.mitocode.microservices.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.microservices.userservice.model.entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.setBasePath("/mitocode"); //Ahora el path seria localhost:9010/mitocode/user
        config.exposeIdsFor(UserEntity.class); //Mostrar ID's en la respuesta
        config.useHalAsDefaultJsonMediaType(false); //Para quitar el nivel de entities en la respuesta
        config.getExposureConfiguration().forDomainType(UserEntity.class).withItemExposure(
                (metadata, httpMethods) -> httpMethods.disable(HttpMethod.DELETE)
        ); //Deshabilitamos un verbo para toda una entidad
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        RepositoryRestConfigurer.super.configureJacksonObjectMapper(objectMapper);
    }
}
