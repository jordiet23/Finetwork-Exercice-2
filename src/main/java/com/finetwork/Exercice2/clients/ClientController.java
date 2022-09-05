package com.finetwork.Exercice2.clients;

import com.finetwork.Exercice2.clients.model.Client;
import com.finetwork.Exercice2.clients.model.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/massive")
public class ClientController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ResponseEntity<Client> createMassiveSms(@RequestBody ClientDto clientDto) throws Exception {
        log.info("MassiveSmsController#createMassiveSms with body : " + clientDto.toJson());

        Client response = clientService.createClient(clientDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
