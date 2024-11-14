package com.example.eazy.mapper;

import com.example.eazy.dto.conta.ContaRequestDTO;
import com.example.eazy.dto.conta.ContaResponseDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.model.Conta;
import com.example.eazy.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ContaMapper {

    public Conta contaEletricaToRequest(ContaRequestDTO contaRequestDTO)
    {
        Conta conta = new Conta();

        conta.setKwh(contaRequestDTO.kwh());
        conta.setValor(contaRequestDTO.valor());
        conta.setData(contaRequestDTO.data());

        return conta;
    }

    public ContaResponseDTO contaEletricaToResponse(Conta conta)
    {
        return new ContaResponseDTO(
                conta.getId(),
                conta.getKwh(),
                conta.getValor(),
                conta.getData(),
                toUsuarioResponseDTO(conta.getUsuario())
        );
    }

    private UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }

}
