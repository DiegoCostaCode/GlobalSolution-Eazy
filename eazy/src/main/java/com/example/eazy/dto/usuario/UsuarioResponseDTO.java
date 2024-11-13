package com.example.eazy.dto.usuario;

public record UsuarioResponseDTO(
        Long id,
        String usuario,
        String email,
        String telefone,
        double potuacao
){}
