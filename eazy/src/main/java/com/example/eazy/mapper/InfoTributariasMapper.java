package com.example.eazy.mapper;

import com.example.eazy.dto.informacoesTributariasDTO.InfoTribuRequestDTO;
import com.example.eazy.dto.informacoesTributariasDTO.InfoTribuResponseDTO;
import com.example.eazy.model.InformacoesTributarias;
import org.springframework.stereotype.Service;

@Service
public class InfoTributariasMapper {

    public InformacoesTributarias requestToInfoTributarias(InfoTribuRequestDTO infoTribuRequestDTO)
    {
        InformacoesTributarias informacoesTributarias = new InformacoesTributarias();

        informacoesTributarias.setEstado(infoTribuRequestDTO.estado());
        informacoesTributarias.setValorKwh(infoTribuRequestDTO.valorKwh());

        return informacoesTributarias;
    }

    public InfoTribuResponseDTO infoTributariasResponseDTO(InformacoesTributarias informacoesTributarias)
    {
        return new InfoTribuResponseDTO(
                informacoesTributarias.getId(),
                informacoesTributarias.getEstado(),
                informacoesTributarias.getValorKwh(),
                informacoesTributarias.getUltimaAtualizacao()
        );
    }
}
