package com.hlstudios.orders.services.datasource.client;

import com.hlstudios.orders.entites.Client;
import com.hlstudios.orders.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    final ClientRepository clientRepository;

    public ClientServiceImpl(
            ClientRepository clientRepository
    ) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Client> findAllListed() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<Client> findAllPaged(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Client findById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Client add(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Client update(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Client delete(Client client) {
        Client current = this.findById(client.getId());
        clientRepository.delete(current);
        return current;
    }
}
