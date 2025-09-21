package com.bankx.clientprocessing.mapper;

import com.bankx.clientprocessing.Dto.ClientDto;
import com.bankx.clientprocessing.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientEntity toClientEntity(ClientDto clientDto) {
        return ClientEntity.builder()
                .clientId(clientDto.clientId())
                .userId(clientDto.userId())
                .firstName(clientDto.firstName())
                .middleName(clientDto.middleName())
                .lastName(clientDto.lastName())
                .dateOfBirth(clientDto.dateOfBirth())
                .documentType(clientDto.documentType())
                .documentId(clientDto.documentId())
                .documentPrefix(clientDto.documentPrefix())
                .documentSuffix(clientDto.documentSuffix())
                .build();
    }

    public ClientDto toClientDto(ClientEntity clientEntity) {
        return new ClientDto(
                clientEntity.getClientId(),
                clientEntity.getUserId(),
                clientEntity.getFirstName(),
                clientEntity.getMiddleName(),
                clientEntity.getLastName(),
                clientEntity.getDateOfBirth(),
                clientEntity.getDocumentType(),
                clientEntity.getDocumentId(),
                clientEntity.getDocumentPrefix(),
                clientEntity.getDocumentSuffix()
        );
    }

}
