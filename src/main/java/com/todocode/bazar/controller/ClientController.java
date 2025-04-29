package com.todocode.bazar.controller;

import com.todocode.bazar.dto.ClientDto;
import com.todocode.bazar.dto.UpdateClientDto;
import com.todocode.bazar.service.inteface.IClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final IClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<?> addClient(@Valid @RequestBody UpdateClientDto updateClientDto){
        ClientDto createdClient = clientService.addClient(updateClientDto);
        URI location = URI.create("/products/get" + createdClient.getId());
        return ResponseEntity.created(location).body(createdClient);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/get/{idClient}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long idClient){
        return ResponseEntity.ok(clientService.getClientById(idClient));
    }

    @PutMapping("/update/{idClient}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long idClient,
                                                  @Valid @RequestBody UpdateClientDto updateClientDto){
        return ResponseEntity.ok(clientService.updateClient(idClient, updateClientDto));
    }

    @DeleteMapping("/delete/{idClient}")
    public ResponseEntity<?> deleteClient(@PathVariable Long idClient){
        clientService.deleteClient(idClient);
        return ResponseEntity.noContent().build();
    }
}
