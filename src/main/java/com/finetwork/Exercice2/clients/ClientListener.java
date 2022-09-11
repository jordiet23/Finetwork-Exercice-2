package com.finetwork.Exercice2.clients;

import com.finetwork.Exercice2.clients.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;

@Component
public class ClientListener<T> {

    private T t;

    @Autowired
    public ClientListener() {

    }

    @PostPersist
    private void afterCreation(T t) {
        if (t instanceof Client) {
            sendSMS((Client) t);
        }
    }

    private void sendSMS(Client client) {
        System.out.println("Se va a enviar un mensaje al cliente " + client.getName() + " por SMS al teléfono " + client.getPhone());
    }

}
