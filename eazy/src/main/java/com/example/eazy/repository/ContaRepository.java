package com.example.eazy.repository;

import com.example.eazy.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
