package com.bankx.clientprocessing.controller;

import com.bankx.clientprocessing.Dto.ClientDto;
import com.bankx.clientprocessing.Dto.ProductDto;
import com.bankx.clientprocessing.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
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

    //TODO добавить пагинацию
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(
    ) {
        logger.info("Called: getAllProducts - Controller layer");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto
    ) {
        logger.info("Called: updateProduct - Controller layer");
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable long id
    ) {
        logger.info("Called: deleteProduct - Controller layer");
        productService.deleteProduct(id);
        return  ResponseEntity.ok().build();
    }

}
