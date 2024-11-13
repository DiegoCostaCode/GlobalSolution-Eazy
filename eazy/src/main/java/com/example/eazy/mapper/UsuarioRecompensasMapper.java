package com.example.eazy.mapper;

import com.example.eazy.dto.usuario.UsuarioRequestDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.dto.usuarioRecompensas.UsuarioRecompensasRequestDTO;
import com.example.eazy.dto.usuarioRecompensas.UsuarioRecompensasResponseDTO;
import com.example.eazy.model.Usuario;
import com.example.eazy.model.UsuarioRecompensas;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRecompensasMapper {

    public UsuarioRecompensas usuarioRecompensasToRequest(UsuarioRecompensasRequestDTO usuarioRecompensasRequestDTO)
    {
        UsuarioRecompensas usuarioRecompensas = new UsuarioRecompensas();

        usuarioRecompensas.setUsuario(usuarioRecompensasRequestDTO.id_usuario());
        usuarioRecompensas.setRecompensa(usuarioRecompensasRequestDTO.id_recompesa());

        return usuarioRecompensas;

    }

    public UsuarioRecompensasResponseDTO usuarioRecompensasToResponse(UsuarioRecompensas usuarioRecompensas)
    {
        return new UsuarioRecompensasResponseDTO(
                usuarioRecompensas.getId(),
                usuarioRecompensas.getUsuario(),
                usuarioRecompensas.getRecompensa(),
                usuarioRecompensas.getDataResgate()

        );
    }
}
