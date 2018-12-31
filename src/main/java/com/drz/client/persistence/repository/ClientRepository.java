package com.drz.client.persistence.repository;

import com.drz.client.persistence.entity.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

}
