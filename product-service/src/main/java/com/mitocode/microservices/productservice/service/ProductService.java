package com.mitocode.microservices.productservice.service;

import com.mitocode.microservices.commonmodels.model.entity.ProductEntity;
import com.mitocode.microservices.productservice.model.dto.ProductDTO;
import com.mitocode.microservices.productservice.service.repository.ProductRepository;
import com.mitocode.microservices.productservice.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final UtilMapper utilMapper;
    private final ProductRepository productRepository;

//    @Value("${server.port}")
//    private Integer port;


    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = utilMapper.convertDTOtoEntity(productDTO);
        productRepository.save(productEntity);
//        productDTO.setPort(port);
        return productDTO;
    }


    public List<ProductDTO> getAllProducts() {

        Iterable<ProductEntity> itProducts = productRepository.findAll();
//                .build());

        return StreamSupport.stream(itProducts.spliterator(), false).map(productEntity -> {
            ProductDTO productDTO = ProductDTO.builder().build();
            BeanUtils.copyProperties(productEntity, productDTO);
//            productDTO.setPort(port);


            return productDTO;
        }).collect(Collectors.toList());
    }

    public String updateProduct(String productId, Integer quantity) {

        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        productEntity.setStock(productEntity.getStock() - quantity);
        productRepository.save(productEntity);
        return "OK";

    }

}
