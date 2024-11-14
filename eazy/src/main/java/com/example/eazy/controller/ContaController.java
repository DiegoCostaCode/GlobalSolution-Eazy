package com.example.eazy.controller;

import com.example.eazy.dto.conta.ContaRequestDTO;
import com.example.eazy.dto.conta.ContaResponseDTO;
import com.example.eazy.dto.usuario.UsuarioRequestDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.mapper.ContaMapper;
import com.example.eazy.model.Conta;
import com.example.eazy.model.Usuario;
import com.example.eazy.repository.ContaRepository;
import com.example.eazy.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/conta", produces = {"application/json"})
public class ContaController {

    @Autowired
    private ContaMapper contaMapper;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/{idConta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ContaResponseDTO>> findContaById(@PathVariable Long idConta) {
        Conta contaEncontrada = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrado"));

        ContaResponseDTO contaResponseDTO = contaMapper.contaEletricaToResponse(contaEncontrada);

        return ResponseEntity.ok(
                EntityModel.of(contaResponseDTO,
                        linkTo(methodOn(ContaController.class).findContaById(idConta)).withSelfRel(),
                        linkTo(methodOn(ContaController.class).createConta(null,null)).withRel("POST"),
                        linkTo(methodOn(ContaController.class).updateConta(idConta, null)).withRel("PUT"),
                        linkTo(methodOn(ContaController.class).deleteConta(idConta)).withRel("DELETE")
                ));
    }

    @PostMapping(value = ("/{idUsuario}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContaResponseDTO> createConta(@PathVariable Long idUsuario, @Valid @RequestBody ContaRequestDTO contaRequestDTO) {

    Usuario usuarioConta = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    Conta contaConvertida = contaMapper.contaEletricaToRequest(contaRequestDTO);

    contaConvertida.setUsuario(usuarioConta);
    Conta conta = contaRepository.save(contaConvertida);

    ContaResponseDTO contaResponseDTO = contaMapper.contaEletricaToResponse(conta);

    return new ResponseEntity<>(contaResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = ("/{idConta}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContaResponseDTO> updateConta(@PathVariable Long idConta, @Valid @RequestBody ContaRequestDTO contaRequestDTO) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        conta.setKwh(contaRequestDTO.kwh());
        conta.setValor(contaRequestDTO.valor());
        conta.setData(contaRequestDTO.data());

        Conta contaAtualizada = contaRepository.save(conta);
        ContaResponseDTO contaResponseDTO = contaMapper.contaEletricaToResponse(contaAtualizada);

        return new ResponseEntity<>(contaResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = ("/{idConta}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteConta(@PathVariable Long idConta) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        contaRepository.delete(conta);

        return new ResponseEntity<>("Conta deletada com sucesso", HttpStatus.OK);
    }


}
