package br.com.tgid.javajunior.controllers;

import br.com.tgid.javajunior.models.Client;
import br.com.tgid.javajunior.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.RuntimeErrorException;

@RestController
@RequestMapping(value="api")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping(value="/client/add")
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        try{
            Client createdClient = service.create(client);

            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        }catch(Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
