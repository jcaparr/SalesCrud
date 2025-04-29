package com.todocode.bazar.repository;

import com.todocode.bazar.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    public boolean existsByDni(String dni);
    public boolean existsByDniAndIdNot(String dni, Long id);
}
