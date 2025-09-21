package com.bankx.clientprocessing.mapper;

import com.bankx.clientprocessing.Dto.ClientProductDto;
import com.bankx.clientprocessing.entity.ClientProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientProductMapper {

    public ClientProductEntity toClientProductEntity(ClientProductDto clientProductDto) {
        ClientProductEntity entity = new ClientProductEntity();
        entity.setId(clientProductDto.id());
        entity.setClientId(clientProductDto.clientId());
        entity.setProductId(clientProductDto.productId());
        entity.setOpenDate(clientProductDto.openDate());
        entity.setCloseDate(clientProductDto.closeDate());
        entity.setStatus(clientProductDto.status());
        return entity;
    }

    public ClientProductDto toClientProductDto(ClientProductEntity clientProductEntity) {
        return new ClientProductDto(
                clientProductEntity.getId(),
                clientProductEntity.getClientId(),
                clientProductEntity.getProductId(),
                clientProductEntity.getOpenDate(),
                clientProductEntity.getCloseDate(),
                clientProductEntity.getStatus()
        );
    }
}