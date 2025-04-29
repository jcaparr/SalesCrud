package com.todocode.bazar.repository;

import com.todocode.bazar.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<Sale, Long> {
}
