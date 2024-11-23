package com.example.eazy.repository;

import com.example.eazy.model.Conta;
import com.example.eazy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    List<Conta> findByUsuario(Usuario usuario);
}
