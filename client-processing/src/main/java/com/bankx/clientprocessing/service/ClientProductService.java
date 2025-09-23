package com.bankx.clientprocessing.service;

import com.bankx.clientprocessing.Dto.ClientProductDto;
import com.bankx.clientprocessing.entity.ClientProductEntity;
import com.bankx.clientprocessing.entity.ProductEntity;
import com.bankx.clientprocessing.entity.enums.ClientProductStatus;
import com.bankx.clientprocessing.entity.enums.ProductKey;
import com.bankx.clientprocessing.kafka.Producer;
import com.bankx.clientprocessing.kafka.event.CreateCardEvent;
import com.bankx.clientprocessing.kafka.event.CreateClientCreditProductEvent;
import com.bankx.clientprocessing.mapper.ClientProductEventMapper;
import com.bankx.clientprocessing.mapper.ClientProductMapper;
import com.bankx.clientprocessing.mapper.CreateCardEventMapper;
import com.bankx.clientprocessing.mapper.CreateCreditEventProductMapper;
import com.bankx.clientprocessing.repository.ClientProductRepository;
import com.bankx.clientprocessing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientProductService {

    private final CreateCardEventMapper createCardEventMapper;
    private final CreateCreditEventProductMapper createCreditEventProductMapper;
    private final ClientProductRepository clientProductRepository;
    private final ClientProductMapper clientProductMapper;
    private final ClientProductEventMapper clientProductEventMapper;
    private final Producer producer;
    private final ProductRepository productRepository;
    private static final Logger log = LoggerFactory.getLogger(ClientProductService.class);

    public ClientProductDto createClientProduct(ClientProductDto clientProductDto) {
        log.info("Called: createClientProduct - Service layer");
        ClientProductEntity entity = clientProductMapper.toClientProductEntity(clientProductDto);
        ClientProductEntity savedEntity = clientProductRepository.save(entity);

        if (isDefaultClientProduct(savedEntity)) {
            producer.sendCreateClientProductEvent(
                    clientProductEventMapper.toCreateClientProductEvent(savedEntity)
            );
        }

        if (isCreditClientProduct(savedEntity)) {
            producer.sendCreateClientCreditProductEvent(
                    createCreditEventProductMapper.toCreateClientCreditProductEvent(savedEntity)
            );
        }

        if (isClientProductCard(savedEntity)) {
            producer.sendCreateCardEvent(
                    createCardEventMapper.toCreateCardEventMapper(savedEntity)
            );
        }

        return clientProductMapper.toClientProductDto(savedEntity);
    }

    private boolean isClientProductCard(ClientProductEntity entity) {
        ProductEntity productEntity = productRepository.findById(entity.getProductId()).get();
        ProductKey productKey = productEntity.getKey();
        return  productKey.equals(ProductKey.AC) ||
                productKey.equals(ProductKey.CC) ||
                productKey.equals(ProductKey.DC);

    }

    private boolean isCreditClientProduct(ClientProductEntity entity) {
        ProductEntity productEntity = productRepository.findById(entity.getProductId()).get();
        ProductKey productKey = productEntity.getKey();
        return  productKey.equals(ProductKey.IPO) ||
                productKey.equals(ProductKey.PC) ||
                productKey.equals(ProductKey.AC);
    }

    private boolean isDefaultClientProduct(ClientProductEntity entity) {
        ProductEntity productEntity = productRepository.findById(entity.getProductId()).get();
        ProductKey productKey = productEntity.getKey();
        return  productKey.equals(ProductKey.DC) ||
                productKey.equals(ProductKey.CC) ||
                productKey.equals(ProductKey.NS) ||
                productKey.equals(ProductKey.PENS);
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

    public List<ClientProductDto> getClientProductsByStatus(ClientProductStatus status) {
        log.info("Called: getClientProductsByStatus - Service layer, status: {}", status);
        return clientProductRepository.findByClientProductStatus(status).stream()
                .map(clientProductMapper::toClientProductDto)
                .collect(Collectors.toList());
    }

    public ClientProductDto closeClientProduct(Long id) {
        log.info("Called: closeClientProduct - Service layer, id: {}", id);
        ClientProductEntity entity = clientProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client product not found with id: " + id));

        entity.setClientProductStatus(ClientProductStatus.CLOSED);
        entity.setCloseDate(java.time.LocalDate.now());

        ClientProductEntity updatedEntity = clientProductRepository.save(entity);
        return clientProductMapper.toClientProductDto(updatedEntity);
    }
}