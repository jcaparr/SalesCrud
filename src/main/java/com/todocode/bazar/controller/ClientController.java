package com.todocode.bazar.controller;

import com.todocode.bazar.dto.response.ClientResponseDto;
import com.todocode.bazar.dto.request.ClientRequestDto;
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
    public ResponseEntity<?> addClient(@Valid @RequestBody ClientRequestDto clientRequestDto){
        ClientResponseDto createdClient = clientService.addClient(clientRequestDto);
        URI location = URI.create("/products/get" + createdClient.getId());
        return ResponseEntity.created(location).body(createdClient);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<ClientResponseDto>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/get/{idClient}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable Long idClient){
        return ResponseEntity.ok(clientService.getClientById(idClient));
    }

    @PutMapping("/update/{idClient}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable Long idClient,
                                                          @Valid @RequestBody ClientRequestDto clientRequestDto){
        return ResponseEntity.ok(clientService.updateClient(idClient, clientRequestDto));
    }

    @DeleteMapping("/delete/{idClient}")
    public ResponseEntity<?> deleteClient(@PathVariable Long idClient){
        clientService.deleteClient(idClient);
        return ResponseEntity.noContent().build();
    }
}
