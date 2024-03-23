package com.mitocode.microservices.productservice.service;

import com.mitocode.microservices.commonmodels.model.entity.AuditInfo;
import com.mitocode.microservices.commonmodels.model.entity.ProductEntity;
import com.mitocode.microservices.productservice.model.dto.ProductDTO;
import com.mitocode.microservices.productservice.service.repository.ProductRepository;
import com.mitocode.microservices.productservice.util.KafkaUtil;
import com.mitocode.microservices.productservice.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final UtilMapper utilMapper;
    private final ProductRepository productRepository;
    private final KafkaUtil kafkaUtil;

    @Value("${server.port}")
    private Integer port;

    public List<ProductDTO> getAllProducts() {
        Iterable<ProductEntity> itProducts = productRepository.findAll();
//                .build());
        return StreamSupport.stream(itProducts.spliterator(), false).map(productEntity -> {
            ProductDTO productDTO = utilMapper.convertEntityToDTO(productEntity);
            productDTO.setPort(port);
            kafkaUtil.sendMessage(productEntity);
            return productDTO;
        }).collect(Collectors.toList());
    }
}
