package com.example.eazy.mapper;

import com.example.eazy.dto.informacoesTributariasDTO.InfoTributariasRequestDTO;
import com.example.eazy.dto.informacoesTributariasDTO.InfoTributariasResponseDTO;
import com.example.eazy.dto.usuario.UsuarioRequestDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.model.InformacoesTributarias;
import com.example.eazy.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class InfoTributariasMapper {

    public InformacoesTributarias requestToInfoTributarias(InfoTributariasRequestDTO infoTributariasRequestDTO)
    {
        InformacoesTributarias informacoesTributarias = new InformacoesTributarias();

        informacoesTributarias.setEstado(infoTributariasRequestDTO.estado());
        informacoesTributarias.setValorKwh(infoTributariasRequestDTO.valorKwh());

        return informacoesTributarias;
    }

    public InfoTributariasResponseDTO infoTributariasResponseDTO(InformacoesTributarias informacoesTributarias)
    {
        return new InfoTributariasResponseDTO(
                informacoesTributarias.getId(),
                informacoesTributarias.getEstado(),
                informacoesTributarias.getValorKwh(),
                informacoesTributarias.getUltimaAtualizacao()
        );
    }
}
