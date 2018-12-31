package com.drz.client.persistence.sender;

import com.drz.client.dto.client.ClientDTO;

public interface ClientSender {

    public void sendSave(ClientDTO clientDTO);

    public void sendDelete(ClientDTO clientDTO);
}
