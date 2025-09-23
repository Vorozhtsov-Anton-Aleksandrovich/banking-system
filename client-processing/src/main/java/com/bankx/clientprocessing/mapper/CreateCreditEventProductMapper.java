package com.bankx.clientprocessing.mapper;

import com.bankx.clientprocessing.entity.ClientProductEntity;
import com.bankx.clientprocessing.kafka.event.CreateClientCreditProductEvent;
import org.springframework.stereotype.Component;

@Component
public class CreateCreditEventProductMapper {

    public CreateClientCreditProductEvent toCreateClientCreditProductEvent(
            ClientProductEntity clientProductEntity
    ) {
        return new CreateClientCreditProductEvent(clientProductEntity.getClientId());
    }

}
