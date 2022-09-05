package com.finetwork.Exercice2.clients;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finetwork.Exercice2.clients.model.Client;
import com.finetwork.Exercice2.clients.model.ClientDto;
import com.finetwork.Exercice2.clients.model.ClientRepository;
import com.finetwork.Exercice2.clients.model.ClientUpdateDto;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getAllClients(Integer page, Integer size, String order) {
        Sort sort = "desc".equalsIgnoreCase(order) ? Sort.by(Sort.Order.desc("createdAt")) : Sort.by(Sort.Order.asc("createdAt"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Client> clients;

        clients = clientRepository.findAll(pageable);

        return clients;
    }

    public Client getCLientById(Long id){
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    public Client createClient(ClientDto clientDto) {
        Client client = new Client();

        client.setDni(clientDto.getDni());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setName(clientDto.getName());

        client = clientRepository.save(client);

        return client;
    }

    public Boolean deleteClientById(Long id){
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Client updateClientById(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()) {
            Client client = optionalClient.get();
            ClientUpdateDto clientUpdateDto = new ClientUpdateDto(client.getName(), client.getPhone());
            ClientUpdateDto clientPached = applyPatchToCustomer(patch, clientUpdateDto);
            client.setName(clientPached.getName());
            client.setPhone(clientPached.getPhone());
            return clientRepository.save(client);
        }
        return null;
    }

    private ClientUpdateDto applyPatchToCustomer(
            JsonPatch patch, ClientUpdateDto targetClient) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetClient, JsonNode.class));
        return objectMapper.treeToValue(patched, ClientUpdateDto.class);
    }

}
