package com.hlstudios.orders.services.web.client;

import com.hlstudios.orders.dto.ClientDto;
import com.hlstudios.orders.entites.Client;
import com.hlstudios.orders.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ClientServiceWeb {

    public List<ClientDto> listClients();

    public Page<ClientDto> pageClients(int page, int size);

    public ClientDto findClientById(UUID id);

    public ClientDto addClient(ClientDto client);

    public ClientDto updateClient(ClientDto client);

    public ClientDto deleteClient(UUID id);

}
