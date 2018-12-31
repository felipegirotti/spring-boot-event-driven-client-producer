package com.drz.client.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientsDTO {

    private int size;

    private int totalPages;

    private List<ClientDTO> content;

    private int currentPageNumber;

    private Long totalElements;

}
