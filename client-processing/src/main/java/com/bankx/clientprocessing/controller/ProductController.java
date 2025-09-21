package com.bankx.clientprocessing.controller;

import com.bankx.clientprocessing.Dto.ProductDto;
import com.bankx.clientprocessing.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto
    ) {
        logger.info("Called: createProduct - Controller layer");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(productDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        logger.info("Called: getAllProducts - Controller layer");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(
            @PathVariable Long id
    ) {
        logger.info("Called: getProductById - Controller layer, id: {}", id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto
    ) {
        logger.info("Called: updateProduct - Controller layer, id: {}", id);
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        logger.info("Called: deleteProduct - Controller layer, id: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductDto>> getProductsByName(
            @PathVariable String name
    ) {
        logger.info("Called: getProductsByName - Controller layer, name: {}", name);
        return ResponseEntity.ok(productService.getProductsByName(name));
    }
}