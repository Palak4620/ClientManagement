package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/clients")
public class ClientController {

   @Autowired
	private ClientService clientService;
   
   @Autowired
   private ClientRepository clientRepository;


    @PostMapping
    public Client createClient(@Valid @RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @GetMapping("/active")
    public List<Client> getActiveClients() {
        return clientService.getAllActiveClients();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchClients(@RequestParam String query) {
        List<Client> results = clientRepository.findByEmailIdContainingIgnoreCaseOrMobileNumberContaining(query, query);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/deactivate/{id}")
    public String deactivateClient(@PathVariable Long id) {
        clientService.deactivateClient(id);
        return "Client deactivated successfully";
    }
    
    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}