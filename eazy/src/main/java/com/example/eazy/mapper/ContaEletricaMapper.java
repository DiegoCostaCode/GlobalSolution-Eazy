package com.example.eazy.mapper;

import com.example.eazy.dto.conta.ContaRequestDTO;
import com.example.eazy.dto.conta.ContaResponseDTO;
import com.example.eazy.dto.usuario.UsuarioRequestDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.model.ContaEletrica;
import com.example.eazy.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ContaEletricaMapper {

    public ContaEletrica contaEletricaToRequest(ContaRequestDTO contaRequestDTO)
    {
        ContaEletrica contaEletrica = new ContaEletrica();

        contaEletrica.setKwh(contaRequestDTO.kwh());
        contaEletrica.setValor(contaRequestDTO.valor());
        contaEletrica.setData(contaRequestDTO.data());

        return contaEletrica;
    }

    public ContaResponseDTO contaEletricaToResponse(ContaEletrica contaEletrica)
    {
        return new ContaResponseDTO(
                contaEletrica.getId(),
                contaEletrica.getKwh(),
                contaEletrica.getValor(),
                contaEletrica.getData()
        );
    }

}
