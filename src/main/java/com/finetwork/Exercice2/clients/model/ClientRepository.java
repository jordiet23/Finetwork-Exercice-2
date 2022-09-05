package com.finetwork.Exercice2.clients.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {


}
