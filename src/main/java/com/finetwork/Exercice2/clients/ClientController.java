package com.finetwork.Exercice2.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.finetwork.Exercice2.clients.model.Client;
import com.finetwork.Exercice2.clients.model.ClientDto;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Page<Client>> getAllClients(@RequestParam(defaultValue = "0") Integer page,
                                                      @RequestParam(defaultValue = "10") Integer size,
                                                      @RequestParam(defaultValue = "asc") String order) {
        return new ResponseEntity<>(clientService.getAllClients(page, size, order), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client response = clientService.getCLientById(id);
        if (response == null) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Client> createMassiveSms(@RequestBody ClientDto clientDto) throws Exception {
        log.info("MassiveSmsController#createMassiveSms with body : " + clientDto.toJson());

        Client response = clientService.createClient(clientDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (!clientService.deleteClientById(id)) {
            return new ResponseEntity<>("KO", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Client> updateCustomer(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            Client client = clientService.updateClientById(id, patch);
            if (client == null) {
                return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
