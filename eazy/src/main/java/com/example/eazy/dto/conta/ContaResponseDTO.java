package com.example.eazy.dto.conta;

import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.model.Usuario;

import java.sql.Date;

public record ContaResponseDTO
        (
                Long id,
                double kwh,
                double valor,
                Date data,
                UsuarioResponseDTO usuario
        ){}
