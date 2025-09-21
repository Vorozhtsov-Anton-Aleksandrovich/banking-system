package com.bankx.clientprocessing.service;

import com.bankx.clientprocessing.Dto.ProductDto;
import com.bankx.clientprocessing.entity.ProductEntity;
import com.bankx.clientprocessing.mapper.ProductMapper;
import com.bankx.clientprocessing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductDto createProduct(ProductDto productDto) {
        log.info("Called: createProduct - Service layer");
        ProductEntity productEntity = productMapper.toProductEntity(productDto);
        ProductEntity savedEntity = productRepository.save(productEntity);
        return productMapper.toProductDto(savedEntity);
    }

    public List<ProductDto> getAllProducts() {
        log.info("Called: getAllProducts - Service layer");
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        log.info("Called: getProductById - Service layer, id: {}", id);
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toProductDto(productEntity);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        log.info("Called: updateProduct - Service layer, id: {}", id);

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        ProductEntity productEntity = productMapper.toProductEntity(productDto);
        productEntity.setId(id);
        ProductEntity updatedEntity = productRepository.save(productEntity);
        return productMapper.toProductDto(updatedEntity);
    }

    public void deleteProduct(Long id) {
        log.info("Called: deleteProduct - Service layer, id: {}", id);
        productRepository.deleteById(id);
    }

    public List<ProductDto> getProductsByName(String name) {
        log.info("Called: getProductsByName - Service layer, name: {}", name);
        return productRepository.findAll().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }
}