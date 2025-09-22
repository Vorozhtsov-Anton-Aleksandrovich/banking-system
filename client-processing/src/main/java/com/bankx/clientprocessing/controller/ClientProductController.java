package com.bankx.clientprocessing.controller;

import com.bankx.clientprocessing.Dto.ClientProductDto;
import com.bankx.clientprocessing.entity.enums.ClientProductStatus;
import com.bankx.clientprocessing.service.ClientProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-products")
@RequiredArgsConstructor
public class ClientProductController {
    private static final Logger logger = LoggerFactory.getLogger(ClientProductController.class);

    private final ClientProductService clientProductService;

    @PostMapping
    public ResponseEntity<ClientProductDto> createClientProduct(
            @RequestBody ClientProductDto clientProductDto
    ) {
        logger.info("Called: createClientProduct - Controller layer");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientProductService.createClientProduct(clientProductDto));
    }

    @GetMapping
    public ResponseEntity<List<ClientProductDto>> getAllClientProducts() {
        logger.info("Called: getAllClientProducts - Controller layer");
        return ResponseEntity.ok(clientProductService.getAllClientProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientProductDto> getClientProductById(
            @PathVariable Long id
    ) {
        logger.info("Called: getClientProductById - Controller layer, id: {}", id);
        return ResponseEntity.ok(clientProductService.getClientProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientProductDto> updateClientProduct(
            @PathVariable Long id,
            @RequestBody ClientProductDto clientProductDto
    ) {
        logger.info("Called: updateClientProduct - Controller layer, id: {}", id);
        return ResponseEntity.ok(clientProductService.updateClientProduct(id, clientProductDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientProduct(
            @PathVariable Long id
    ) {
        logger.info("Called: deleteClientProduct - Controller layer, id: {}", id);
        clientProductService.deleteClientProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ClientProductDto>> getClientProductsByClientId(
            @PathVariable Long clientId
    ) {
        logger.info("Called: getClientProductsByClientId - Controller layer, clientId: {}", clientId);
        return ResponseEntity.ok(clientProductService.getClientProductsByClientId(clientId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ClientProductDto>> getClientProductsByStatus(
            @PathVariable ClientProductStatus status
    ) {
        logger.info("Called: getClientProductsByStatus - Controller layer, status: {}", status);
        return ResponseEntity.ok(clientProductService.getClientProductsByStatus(status));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<ClientProductDto> closeClientProduct(
            @PathVariable Long id
    ) {
        logger.info("Called: closeClientProduct - Controller layer, id: {}", id);
        return ResponseEntity.ok(clientProductService.closeClientProduct(id));
    }
}