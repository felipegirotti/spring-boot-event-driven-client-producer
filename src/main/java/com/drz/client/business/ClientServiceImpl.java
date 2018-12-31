package com.drz.client.business;

import com.drz.client.dto.client.ClientDTO;
import com.drz.client.dto.client.ClientsDTO;
import com.drz.client.persistence.entity.ClientNotFoundException;
import com.drz.client.persistence.repository.ClientRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepositoryService clientRepositoryService;

    @Autowired
    public ClientServiceImpl(ClientRepositoryService clientRepositoryService) {
        this.clientRepositoryService = clientRepositoryService;
    }

    @Override
    public ClientDTO save(ClientDTO client) throws ClientNotFoundException {
        return clientRepositoryService.save(client);
    }

    @Override
    public ClientDTO find(Long id) throws ClientNotFoundException {
        return clientRepositoryService.find(id);
    }

    @Override
    public ClientsDTO getClients(Pageable pageRequest) {
        return clientRepositoryService.getClients(pageRequest);
    }

    @Override
    public void delete(Long id) throws ClientNotFoundException {
        clientRepositoryService.delete(id);
    }
}
