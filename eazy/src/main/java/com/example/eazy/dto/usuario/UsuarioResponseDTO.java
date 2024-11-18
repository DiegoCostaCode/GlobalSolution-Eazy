package com.example.eazy.dto.usuario;

import com.example.eazy.model.Conta;
import com.example.eazy.model.InformacoesTributarias;

import java.util.List;

public record UsuarioResponseDTO(
        Long id,
        String usuario,
        String email,
        String telefone,
        InformacoesTributarias informacoesTributarias
){}
