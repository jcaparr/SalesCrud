package com.todocode.bazar.service.implementation;

import com.todocode.bazar.dto.ClientDto;
import com.todocode.bazar.dto.UpdateClientDto;
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
    public ClientDto addClient(UpdateClientDto updateClientDto){
        if(clientRepository.existsByDni(updateClientDto.getDni())){
            throw new AlreadyExist("Already exist a client with dni: " + updateClientDto.getDni());
        }
        Client client = new Client();
        client.setName(updateClientDto.getName());
        client.setLastName(updateClientDto.getLastName());
        client.setDni(updateClientDto.getDni());

        clientRepository.save(client);
        return mapperUtils.mapEntityToDto(client, ClientDto.class);
    }

    @Override
    public List<ClientDto> getAllClients(){
        List<Client> clientList = clientRepository.findAll();
        if(clientList.isEmpty()){
            throw new NotFoundException("No clients found");
        }
        return mapperUtils.mapEntityListToDtoList(clientList, ClientDto.class);
    }

    @Override
    public ClientDto getClientById(Long idClient){
        Client client = clientRepository.findById(idClient)
                .orElseThrow(()-> new NotFoundException("No client found with id: " + idClient));
        return mapperUtils.mapEntityToDto(client, ClientDto.class);
    }

    @Override
    public ClientDto updateClient(Long idClient, UpdateClientDto updateClientDto){
        Client clientBbdd = clientRepository.findById(idClient)
                .orElseThrow(() -> new NotFoundException("No client found with id: " + idClient));

        if (clientRepository.existsByDniAndIdNot(updateClientDto.getDni(), idClient)) {
            throw new AlreadyExist("Already exist a client with dni: " + updateClientDto.getDni());
        }

        clientBbdd.setName(updateClientDto.getName());
        clientBbdd.setLastName(updateClientDto.getLastName());
        clientBbdd.setDni(updateClientDto.getDni());
        clientRepository.save(clientBbdd);

        return mapperUtils.mapEntityToDto(clientBbdd, ClientDto.class);
    }

    @Override
    public void deleteClient(Long idClient){
        clientRepository.findById(idClient)
                .orElseThrow(() -> new NotFoundException("No client found with id: " + idClient));

        clientRepository.deleteById(idClient);
    }
}
