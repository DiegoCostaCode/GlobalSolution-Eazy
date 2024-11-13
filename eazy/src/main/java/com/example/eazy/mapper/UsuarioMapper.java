package com.example.eazy.mapper;

import com.example.eazy.dto.usuario.UsuarioRequestDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper {
    public Usuario requestToUsuario(UsuarioRequestDTO usuarioRequestDTO)
    {
        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioRequestDTO.usuario());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setTelefone(usuarioRequestDTO.telefone());
        usuario.setSenha(usuarioRequestDTO.senha());

        return usuario;
    }

    public UsuarioResponseDTO usuarioToResponse(Usuario usuario)
    {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getPontuacao()
        );
    }
}
