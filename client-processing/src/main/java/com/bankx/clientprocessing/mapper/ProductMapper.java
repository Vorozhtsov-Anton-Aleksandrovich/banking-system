package com.bankx.clientprocessing.mapper;

import com.bankx.clientprocessing.Dto.ProductDto;
import com.bankx.clientprocessing.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity toProductEntity(ProductDto productDto) {
        return ProductEntity.builder()
                .name(productDto.name())
                .key(productDto.key())
                .createDate(productDto.createDate())
                .productId(productDto.productId())
                .build();
    }

    public ProductDto toProductDto(ProductEntity productEntity) {
        return new ProductDto(
                productEntity.getName(),
                productEntity.getKey(),
                productEntity.getCreateDate(),
                productEntity.getProductId()
        );
    }
}
