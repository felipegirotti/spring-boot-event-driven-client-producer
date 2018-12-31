package com.drz.client.persistence.entity.factory;

import com.drz.client.dto.client.ClientDTO;
import com.drz.client.persistence.entity.Client;

public class ClientFactory {

    public static Client create(ClientDTO clientDTO) {
        return new Client(clientDTO.getId(), clientDTO.getName());

    }
}
