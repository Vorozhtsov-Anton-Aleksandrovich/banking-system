package com.bankx.bankingsystem.clientprocessing.service;

import com.bankx.bankingsystem.clientprocessing.entity.Client;
import com.bankx.bankingsystem.clientprocessing.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}