package com.finetwork.Exercice2.clients;


import com.finetwork.Exercice2.clients.model.Client;
import com.finetwork.Exercice2.clients.model.ClientDto;
import com.finetwork.Exercice2.clients.model.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public Client createClient(ClientDto clientDto) {
        Client client = new Client();

        return client;
    }

}
