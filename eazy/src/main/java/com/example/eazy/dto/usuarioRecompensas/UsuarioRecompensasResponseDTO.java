package com.example.eazy.dto.usuarioRecompensas;

import com.example.eazy.model.Recompensas;
import com.example.eazy.model.Usuario;

import java.sql.Date;

public record UsuarioRecompensasResponseDTO
        (
                Long id,
                Usuario id_usuario,
                Recompensas id_recompensa,
                Date dataResgate
        ){
}
