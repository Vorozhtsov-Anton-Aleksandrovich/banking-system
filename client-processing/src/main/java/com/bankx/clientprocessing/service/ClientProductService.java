package com.bankx.clientprocessing.service;

import com.bankx.clientprocessing.Dto.ClientProductDto;
import com.bankx.clientprocessing.entity.ClientProductEntity;
import com.bankx.clientprocessing.entity.Status;
import com.bankx.clientprocessing.mapper.ClientProductMapper;
import com.bankx.clientprocessing.repository.ClientProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientProductService {

    private final ClientProductRepository clientProductRepository;
    private final ClientProductMapper clientProductMapper;
    private static final Logger log = LoggerFactory.getLogger(ClientProductService.class);

    public ClientProductDto createClientProduct(ClientProductDto clientProductDto) {
        log.info("Called: createClientProduct - Service layer");
        ClientProductEntity entity = clientProductMapper.toClientProductEntity(clientProductDto);
        ClientProductEntity savedEntity = clientProductRepository.save(entity);
        return clientProductMapper.toClientProductDto(savedEntity);
    }

    public List<ClientProductDto> getAllClientProducts() {
        log.info("Called: getAllClientProducts - Service layer");
        List<ClientProductEntity> entities = clientProductRepository.findAll();
        return entities.stream()
                .map(clientProductMapper::toClientProductDto)
                .collect(Collectors.toList());
    }

    public ClientProductDto getClientProductById(Long id) {
        log.info("Called: getClientProductById - Service layer, id: {}", id);
        ClientProductEntity entity = clientProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client product not found with id: " + id));
        return clientProductMapper.toClientProductDto(entity);
    }

    public ClientProductDto updateClientProduct(Long id, ClientProductDto clientProductDto) {
        log.info("Called: updateClientProduct - Service layer, id: {}", id);

        if (!clientProductRepository.existsById(id)) {
            throw new RuntimeException("Client product not found with id: " + id);
        }

        ClientProductEntity entity = clientProductMapper.toClientProductEntity(clientProductDto);
        entity.setId(id);
        ClientProductEntity updatedEntity = clientProductRepository.save(entity);
        return clientProductMapper.toClientProductDto(updatedEntity);
    }

    public void deleteClientProduct(Long id) {
        log.info("Called: deleteClientProduct - Service layer, id: {}", id);
        clientProductRepository.deleteById(id);
    }

    public List<ClientProductDto> getClientProductsByClientId(Long clientId) {
        log.info("Called: getClientProductsByClientId - Service layer, clientId: {}", clientId);
        return clientProductRepository.findByClientId(clientId).stream()
                .map(clientProductMapper::toClientProductDto)
                .collect(Collectors.toList());
    }

    public List<ClientProductDto> getClientProductsByStatus(Status status) {
        log.info("Called: getClientProductsByStatus - Service layer, status: {}", status);
        return clientProductRepository.findByStatus(status).stream()
                .map(clientProductMapper::toClientProductDto)
                .collect(Collectors.toList());
    }

    public ClientProductDto closeClientProduct(Long id) {
        log.info("Called: closeClientProduct - Service layer, id: {}", id);
        ClientProductEntity entity = clientProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client product not found with id: " + id));

        entity.setStatus(Status.CLOSED);
        entity.setCloseDate(java.time.LocalDate.now());

        ClientProductEntity updatedEntity = clientProductRepository.save(entity);
        return clientProductMapper.toClientProductDto(updatedEntity);
    }
}