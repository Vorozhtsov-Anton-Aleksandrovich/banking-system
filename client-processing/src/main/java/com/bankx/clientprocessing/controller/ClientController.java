package com.bankx.clientprocessing.controller;


import com.bankx.clientprocessing.Dto.ClientDto;
import com.bankx.clientprocessing.entity.ClientEntity;
import com.bankx.clientprocessing.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDto> createClient(
            @RequestBody ClientDto clientDto
    ) {
        logger.info("Called: createClient - Service layer");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.createClient(clientDto));
    }

}
