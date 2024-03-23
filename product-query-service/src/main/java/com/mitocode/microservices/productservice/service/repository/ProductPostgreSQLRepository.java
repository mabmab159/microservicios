package com.mitocode.microservices.productservice.service.repository;

import com.mitocode.microservices.productservice.model.entity.ProductPostgreSQLEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPostgreSQLRepository extends CrudRepository<ProductPostgreSQLEntity, String> {
}
