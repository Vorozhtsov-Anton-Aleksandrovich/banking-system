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
        productRepository.save(productEntity);
        return productDto;
    }


    public List<ProductDto> getAllProducts() {
        log.info("Called: getAllProducts - Service layer");
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities
                .stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        log.info("Called: updateProduct - Controller layer");
        ProductEntity productEntityUpdate = productMapper.toProductEntity(productDto);
        productEntityUpdate.setId(id);
        productRepository.save(productEntityUpdate);
        return productDto;
    }

    public void deleteProduct(Long id) {
        log.info("Called: deleteProduct - Controller layer");
        productRepository.deleteById(id);
    }

}
