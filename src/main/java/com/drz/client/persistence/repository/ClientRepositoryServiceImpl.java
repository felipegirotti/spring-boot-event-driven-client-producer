package com.drz.client.persistence.repository;

import com.drz.client.dto.client.ClientDTO;
import com.drz.client.dto.client.ClientDTOFactory;
import com.drz.client.dto.client.ClientsDTO;
import com.drz.client.persistence.entity.Client;
import com.drz.client.persistence.entity.ClientNotFoundException;
import com.drz.client.persistence.entity.factory.ClientFactory;
import com.drz.client.persistence.sender.ClientSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientRepositoryServiceImpl implements ClientRepositoryService {

    private ClientRepository clientRepository;

    private ClientSender sender;

    @Autowired
    public ClientRepositoryServiceImpl(ClientRepository clientRepository, ClientSender clientSender) {
        this.clientRepository = clientRepository;
        this.sender = clientSender;
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) throws ClientNotFoundException {
        Client client = ClientFactory.create(clientDTO);
        if (client.getId() != null) {
            find(clientDTO.getId());
        }
        clientDTO.setId(clientRepository.save(client).getId());
        sender.sendSave(clientDTO);

        return clientDTO;
    }

    @Override
    public ClientDTO find(Long id) throws ClientNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return ClientDTOFactory.create(optionalClient.get());
        }

        throw new ClientNotFoundException();
    }

    @Override
    public void delete(Long id) throws ClientNotFoundException {
        ClientDTO clientDTO = find(id);
        clientRepository.delete(ClientFactory.create(clientDTO));
        sender.sendDelete(clientDTO);
    }

    @Override
    public ClientsDTO getClients(Pageable pageRequest) {
        Page<Client> page = clientRepository.findAll(pageRequest);
        List<ClientDTO> clients = page.getContent().stream().map(ClientDTOFactory::create).collect(Collectors.toList());

        return new ClientsDTO(
                page.getSize(),
                page.getTotalPages(),
                clients,
                page.getNumber(),
                page.getTotalElements()
        );
    }
}
