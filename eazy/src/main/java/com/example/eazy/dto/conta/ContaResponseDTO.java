package com.example.eazy.dto.conta;

import com.example.eazy.dto.usuario.UsuarioResponseDTO;

import java.util.Date;

public record ContaResponseDTO
        (
                Long id,
                double kwh,
                double valor,
                Date data,
                UsuarioResponseDTO usuario
        ){}
