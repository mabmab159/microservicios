package com.mitocode.microservices.productservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductPostgreSQLEntity {
    @Id
    private String productId;
    private String productName;
    private String productType;
    private Long price;
    private Integer stock;
}
