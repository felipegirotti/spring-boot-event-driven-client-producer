package com.drz.client.persistence.repository;

import com.drz.client.dto.client.ClientDTO;
import com.drz.client.dto.client.ClientsDTO;
import com.drz.client.persistence.entity.ClientNotFoundException;
import org.springframework.data.domain.Pageable;

public interface ClientRepositoryService {

    public ClientDTO save(ClientDTO client) throws ClientNotFoundException;

    public ClientDTO find(Long id) throws ClientNotFoundException;

    public void delete(Long id) throws ClientNotFoundException;

    public ClientsDTO getClients(Pageable pageRequest);
}
