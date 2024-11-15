package com.example.eazy.repository;

import com.example.eazy.model.Conta;
import com.example.eazy.model.Enum_estado;
import com.example.eazy.model.InformacoesTributarias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ContaRepository extends JpaRepository<Conta, Long> {
    List<Conta> findByIdUsuario(long idUsuario);
}
