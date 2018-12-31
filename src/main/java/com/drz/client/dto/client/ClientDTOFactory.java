package com.drz.client.dto.client;

import com.drz.client.persistence.entity.Client;

public class ClientDTOFactory {

    public static ClientDTO create(Client client) {
        return new ClientDTO(client.getId(), client.getName());
    }
}
