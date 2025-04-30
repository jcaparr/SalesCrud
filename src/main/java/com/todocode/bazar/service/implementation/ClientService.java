package com.todocode.bazar.service.implementation;

import com.todocode.bazar.dto.request.ClientRequestDto;
import com.todocode.bazar.dto.response.ClientResponseDto;
import com.todocode.bazar.exception.AlreadyExist;
import com.todocode.bazar.exception.NotFoundException;
import com.todocode.bazar.model.Client;
import com.todocode.bazar.repository.IClientRepository;
import com.todocode.bazar.service.inteface.IClientService;
import com.todocode.bazar.utils.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private final MapperUtils mapperUtils;
    private final IClientRepository clientRepository;

    @Override
    public ClientResponseDto addClient(ClientRequestDto clientRequestDto) {
        if (clientRepository.existsByDni(clientRequestDto.getDni())) {
            throw new AlreadyExist("Already exist a client with dni: " + clientRequestDto.getDni());
        }
        Client client = new Client();
        client.setName(clientRequestDto.getName());
        client.setLastName(clientRequestDto.getLastName());
        client.setDni(clientRequestDto.getDni());
        client.setSales(List.of());

        clientRepository.save(client);
        return mapperUtils.clientToResponse(client);
    }

    @Override
    public List<ClientResponseDto> getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        if (clientList.isEmpty()) {
            throw new NotFoundException("No clients found");
        }
        return clientList.stream().map(mapperUtils::clientToResponse).toList();
    }

    @Override
    public ClientResponseDto getClientById(Long idClient) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new NotFoundException("No client found with id: " + idClient));
        return mapperUtils.clientToResponse(client);
    }

    @Override
    public ClientResponseDto updateClient(Long idClient, ClientRequestDto clientRequestDto) {
        Client clientBbdd = clientRepository.findById(idClient)
                .orElseThrow(() -> new NotFoundException("No client found with id: " + idClient));

        if (clientRepository.existsByDniAndIdNot(clientRequestDto.getDni(), idClient)) {
            throw new AlreadyExist("Already exist a client with dni: " + clientRequestDto.getDni());
        }

        clientBbdd.setName(clientRequestDto.getName());
        clientBbdd.setLastName(clientRequestDto.getLastName());
        clientBbdd.setDni(clientRequestDto.getDni());
        clientRepository.save(clientBbdd);

        return mapperUtils.clientToResponse(clientBbdd);
    }

    @Override
    public void deleteClient(Long idClient) {
        clientRepository.findById(idClient)
                .orElseThrow(() -> new NotFoundException("No client found with id: " + idClient));

        clientRepository.deleteById(idClient);
    }
}
