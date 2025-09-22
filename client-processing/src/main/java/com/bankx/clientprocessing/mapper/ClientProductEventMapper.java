package com.bankx.clientprocessing.mapper;

import com.bankx.clientprocessing.Dto.ProductDto;
import com.bankx.clientprocessing.entity.ClientProductEntity;
import com.bankx.clientprocessing.entity.ProductEntity;
import com.bankx.clientprocessing.kafka.event.CreateClientProductEvent;
import com.bankx.clientprocessing.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientProductEventMapper {

    private final ProductService productService;

    public CreateClientProductEvent toCreateClientProductEvent(
            ClientProductEntity clientProductEntity
    ) {
        CreateClientProductEvent createClientProductEvent
                = new CreateClientProductEvent();
        createClientProductEvent.setClientId(clientProductEntity.getClientId());
        createClientProductEvent.setProductId(clientProductEntity.getProductId());

        ProductDto productDto = productService.getProductById(clientProductEntity.getProductId());
        createClientProductEvent.setProductKey(productDto.key());

        return createClientProductEvent;
    }

    //TODO дописать обратный
}
