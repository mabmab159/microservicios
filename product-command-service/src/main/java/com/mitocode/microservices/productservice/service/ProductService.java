package com.mitocode.microservices.productservice.service;

import com.mitocode.microservices.commonmodels.model.entity.ProductEntity;
import com.mitocode.microservices.productservice.model.dto.ProductDTO;
import com.mitocode.microservices.productservice.model.entity.ProductPostgreSQLEntity;
import com.mitocode.microservices.productservice.service.repository.ProductPostgreSQLRepository;
import com.mitocode.microservices.productservice.service.repository.ProductRepository;
import com.mitocode.microservices.productservice.util.KafkaUtil;
import com.mitocode.microservices.productservice.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final UtilMapper utilMapper;
    private final ProductRepository productRepository;
    private final KafkaUtil kafkaUtil;

    @Value("${server.port}")
    private Integer port;
    private final ProductPostgreSQLRepository productPostgreSQLRepository;

    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = utilMapper.convertDTOtoEntity(productDTO);
        // ProductPostgreSQLEntity productPostgreSQLEntity = utilMapper.convertDTOToEntityPostreSQL(productDTO);
        productRepository.save(productEntity);
        kafkaUtil.sendMessage(productEntity);
        //productPostgreSQLRepository.save(productPostgreSQLEntity);
        productDTO.setPort(port);
        return productDTO;
    }

    public String updateProduct(String productId, Integer quantity) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        productEntity.setStock(productEntity.getStock() - quantity);
        productRepository.save(productEntity);
        return "OK";
    }
}
