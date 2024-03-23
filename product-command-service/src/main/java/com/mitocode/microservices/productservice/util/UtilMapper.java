package com.mitocode.microservices.productservice.util;

import com.mitocode.microservices.commonmodels.model.entity.ProductEntity;
import com.mitocode.microservices.productservice.model.dto.ProductDTO;
import com.mitocode.microservices.productservice.model.entity.ProductPostgreSQLEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UtilMapper {
    public ProductEntity convertDTOtoEntity(ProductDTO productDTO) {
        ProductEntity productEntity = ProductEntity.builder().build();
        BeanUtils.copyProperties(productDTO, productEntity);
        return productEntity;
    }

    public ProductDTO convertEntityToDTO(ProductEntity productEntity) {
        ProductDTO productDTO = ProductDTO.builder().build();
        BeanUtils.copyProperties(productEntity, productDTO);
        return productDTO;
    }

    public ProductPostgreSQLEntity convertDTOToEntityPostreSQL(ProductDTO productDTO) {
        ProductPostgreSQLEntity productPostgreSQLEntity = ProductPostgreSQLEntity.builder().build();
        BeanUtils.copyProperties(productDTO, productPostgreSQLEntity);
        return productPostgreSQLEntity;
    }
}
