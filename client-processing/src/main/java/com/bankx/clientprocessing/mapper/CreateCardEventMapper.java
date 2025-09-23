package com.bankx.clientprocessing.mapper;

import com.bankx.clientprocessing.entity.ClientProductEntity;
import com.bankx.clientprocessing.entity.ProductEntity;
import com.bankx.clientprocessing.kafka.event.CreateCardEvent;
import com.bankx.clientprocessing.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateCardEventMapper {

    private final ProductRepository productRepository;

    public CreateCardEvent toCreateCardEventMapper(
            ClientProductEntity clientProductEntity
    ) {
        ProductEntity productEntity = productRepository.findById(clientProductEntity.getProductId()).get();
        return new  CreateCardEvent(
                clientProductEntity.getClientId(),
                productEntity.getKey()
        );
    }

}
