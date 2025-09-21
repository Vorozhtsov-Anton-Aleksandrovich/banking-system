package com.bankx.clientprocessing.service;


import com.bankx.clientprocessing.Dto.ClientDto;
import com.bankx.clientprocessing.mapper.ClientMapper;
import com.bankx.clientprocessing.entity.ClientEntity;
import com.bankx.clientprocessing.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

        @Service
        @RequiredArgsConstructor
public class ClientService {

        private final ClientRepository clientRepository;
        private final ClientMapper clientMapper;
        private static final Logger log = LoggerFactory.getLogger(ClientService.class);

        public ClientDto createClient(ClientDto clientDto) {
                log.info("Called: createClient - Controller layer");
                ClientEntity clientEntity = clientMapper.toClientEntity(clientDto);
                clientRepository.save(clientEntity);
                return clientDto;
        }



}
