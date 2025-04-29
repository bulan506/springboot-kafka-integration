package com.gringottsbank.controller;

import com.gringottsbank.controller.response.ApiResponse;
import com.gringottsbank.model.Client;
import com.gringottsbank.controller.request.UpdateAddressRequest;
import com.gringottsbank.service.ClientService;
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
    public ResponseEntity<ApiResponse> saveClient(@RequestBody Client client){
            Map<String, Object> response = new HashMap<>();
            clientService.saveClient(client);
            return ResponseEntity.ok(new ApiResponse(200, "Client added successfully"));
    }
    @PatchMapping("/updateAddress")
    public ResponseEntity<ApiResponse> updateAddress(@RequestBody UpdateAddressRequest request) {
        clientService.updateAddresses(request.getClientId(), request.getNewAddresses());
        return ResponseEntity.ok(new ApiResponse(200, "Addresses updated successfully"));
    }
}
