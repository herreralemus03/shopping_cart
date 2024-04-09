package com.hlstudios.orders.services.web.client;

import com.hlstudios.orders.dto.ClientDto;
import com.hlstudios.orders.entites.Client;
import com.hlstudios.orders.services.datasource.client.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientServiceWebImpl implements ClientServiceWeb {

    final ClientService clientService;
    final ModelMapper modelMapper;

    public ClientServiceWebImpl (
            ClientService clientService,
            ModelMapper modelMapper
    ){
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClientDto> listClients() {
        return clientService.findAllListed()
                .stream()
                .map(e -> modelMapper.map(e, ClientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ClientDto> pageClients(int page, int size) {
        return clientService.findAllPaged(PageRequest.of(page, size))
                .map(e -> modelMapper.map(e, ClientDto.class));
    }

    @Override
    public ClientDto findClientById(UUID id) {
        return modelMapper.map(clientService.findById(id), ClientDto.class);
    }

    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        Client result = clientService.add(client);
        return modelMapper.map(result, ClientDto.class);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        Client result = clientService.update(client);
        return modelMapper.map(result, ClientDto.class);
    }

    @Override
    public ClientDto deleteClient(UUID id) {
        ClientDto clientFound = this.findClientById(id);
        Client client = modelMapper.map(clientFound, Client.class);
        Client result = clientService.delete(client);
        return modelMapper.map(result, ClientDto.class);
    }
}
