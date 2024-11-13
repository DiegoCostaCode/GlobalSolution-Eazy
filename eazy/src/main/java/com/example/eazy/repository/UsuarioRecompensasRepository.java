package com.example.eazy.repository;

import com.example.eazy.model.UsuarioRecompensas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioRecompensasRepository extends JpaRepository<UsuarioRecompensas,Long> {
}
