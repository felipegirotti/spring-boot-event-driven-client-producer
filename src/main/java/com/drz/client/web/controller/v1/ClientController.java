package com.drz.client.web.controller.v1;

import com.drz.client.business.ClientService;
import com.drz.client.dto.ErrorResponse;
import com.drz.client.dto.client.ClientDTO;
import com.drz.client.dto.client.ClientsDTO;
import com.drz.client.persistence.entity.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientDTO getPlace(@PathVariable Long id) throws ClientNotFoundException {
        return clientService.find(id);
    }

    @GetMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientsDTO getClients(@PageableDefault(size = 50, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageRequest) {
        return clientService.getClients(pageRequest);
    }

    @PostMapping(value = "/client", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientDTO create(@RequestBody @Valid ClientDTO client) throws ClientNotFoundException {
        client.setId(null);
        return clientService.save(client);
    }

    @PutMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientDTO update(@PathVariable Long id, @RequestBody @Valid ClientDTO client) throws ClientNotFoundException {
        client.setId(id);
        return clientService.save(client);
    }

    @DeleteMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@PathVariable Long id) throws ClientNotFoundException {
        clientService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(ClientNotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getCode());
    }
}
