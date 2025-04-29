package com.todocode.bazar.service.inteface;

import com.todocode.bazar.dto.ClientDto;
import com.todocode.bazar.dto.UpdateClientDto;

import java.util.List;

public interface IClientService {
    public ClientDto addClient(UpdateClientDto updateClientDto);
    public List<ClientDto> getAllClients();
    public ClientDto getClientById(Long client_id);
    public ClientDto updateClient(Long client_id, UpdateClientDto updateClientDto);
    public void deleteClient(Long client_id);
}
