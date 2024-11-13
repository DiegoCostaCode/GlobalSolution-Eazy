package com.example.eazy.repository;

import com.example.eazy.model.ContaEletrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ContaEletricaRepository extends JpaRepository<ContaEletrica, Long> {
}
