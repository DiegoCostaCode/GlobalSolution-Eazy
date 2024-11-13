package com.example.eazy.dto.usuarioRecompensas;


import com.example.eazy.model.Recompensas;
import com.example.eazy.model.Usuario;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRecompensasRequestDTO
        (
                @NotBlank(message = "Não pode ser vazio")
                Usuario id_usuario,
                @NotBlank(message = "Não pode ser vazio")
                Recompensas id_recompesa
        ) {
}
