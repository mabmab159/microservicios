package com.mitocode.microservices.productservice.expose.web;

import com.mitocode.microservices.productservice.model.dto.ProductDTO;
import com.mitocode.microservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/saveProduct")
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        log.info(productDTO.toString());
        return ResponseEntity.ok(productService.saveProduct(productDTO));
    }

    @PutMapping("/product/reserve/{productId}/{quantity}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> reserve(@PathVariable("productId") String productId,
                                          @PathVariable("quantity") Integer quantity) {
        return ResponseEntity.ok(productService.updateProduct(productId, quantity));
    }
}
