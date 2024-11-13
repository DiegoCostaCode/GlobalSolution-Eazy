package com.example.eazy.mapper;

import com.example.eazy.dto.recompensas.RecompensasRequestDTO;
import com.example.eazy.dto.usuario.UsuarioRequestDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.model.Recompensas;
import com.example.eazy.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class RecompensasMapper {
    public Recompensas recompensasToRequest(RecompensasRequestDTO recompensasRequestDTO)
    {
        Recompensas recompensas = new Recompensas();
        recompensas.setDescricao(recompensasRequestDTO.descricao());
        recompensas.setMeta(recompensasRequestDTO.valor());

        return recompensas;
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
