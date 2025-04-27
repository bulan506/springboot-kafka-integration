package com.gringottsbank.controller;

import com.gringottsbank.model.Client;
import com.gringottsbank.service.ClientEventsService;
import com.gringottsbank.service.ClientService;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping(path="/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/saveClient")
    public ResponseEntity<?> saveClient(@RequestBody Client client){
            Map<String, Object> response = new HashMap<>();
            clientService.saveClient(client);
            response.put("message", "Client added successfully");
            response.put("isSuccess", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
