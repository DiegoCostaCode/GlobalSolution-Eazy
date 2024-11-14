package com.example.eazy.dto.usuario;

import java.util.List;

public record UsuarioResponseDTO(
        Long id,
        String usuario,
        String email,
        String telefone
){}
