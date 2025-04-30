package com.todocode.bazar.service.inteface;

import com.todocode.bazar.dto.request.ClientRequestDto;
import com.todocode.bazar.dto.response.ClientResponseDto;

import java.util.List;

public interface IClientService {
    public ClientResponseDto addClient(ClientRequestDto clientRequestDto);

    public List<ClientResponseDto> getAllClients();

    public ClientResponseDto getClientById(Long client_id);

    public ClientResponseDto updateClient(Long client_id, ClientRequestDto clientRequestDto);

    public void deleteClient(Long client_id);
}
