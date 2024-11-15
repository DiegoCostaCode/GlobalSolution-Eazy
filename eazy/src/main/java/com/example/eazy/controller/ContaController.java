package com.example.eazy.controller;

import com.example.eazy.dto.conta.ContaRequestDTO;
import com.example.eazy.dto.conta.ContaResponseDTO;
import com.example.eazy.dto.informacoesTributariasDTO.InfoTribuResponseDTO;
import com.example.eazy.dto.usuario.UsuarioRequestDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.mapper.ContaMapper;
import com.example.eazy.model.Conta;
import com.example.eazy.model.Usuario;
import com.example.eazy.repository.ContaRepository;
import com.example.eazy.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/conta", produces = {"application/json"})
@Tag(name = "Conta", description = "CRUD de contas eletricas")
public class ContaController {

    @Autowired
    private ContaMapper contaMapper;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Trás uma conta pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta encontrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Conta não encontrada.",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/{idConta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ContaResponseDTO>> findContaById(@PathVariable Long idConta) {
        Conta contaEncontrada = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrado"));

        ContaResponseDTO contaResponseDTO = contaMapper.contaEletricaToResponse(contaEncontrada);

        return ResponseEntity.ok(
                EntityModel.of(contaResponseDTO,
                        linkTo(methodOn(ContaController.class).findContaById(idConta)).withSelfRel(),
                        linkTo(methodOn(ContaController.class).findContaByIdUsuario(null)).withRel("GET"),
                        linkTo(methodOn(ContaController.class).createConta(null,null)).withRel("POST"),
                        linkTo(methodOn(ContaController.class).updateConta(idConta, null)).withRel("PUT"),
                        linkTo(methodOn(ContaController.class).deleteConta(idConta)).withRel("DELETE")
                ));
    }

    @Operation(summary = "Trás contas pelo ID do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contas encontradas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Nenhuma conta encontrada.",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/usuario/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<ContaResponseDTO>>> findContaByIdUsuario(@PathVariable Usuario usuario) {

        List<Conta> contas = contaRepository.findByUsuario(usuario);

        if (contas.isEmpty()) {
            throw new RuntimeException("Nenhuma conta encontrada para o usuário");
        }

        List<EntityModel<ContaResponseDTO>> contaResponseDTO = contas.stream()
                .map(conta -> {
                    ContaResponseDTO contaResponse = contaMapper.contaEletricaToResponse(conta);
                    return EntityModel.of(contaResponse,
                            linkTo(methodOn(ContaController.class).findContaByIdUsuario(null)).withSelfRel(),
                            linkTo(methodOn(ContaController.class).findContaById(contaResponse.id())).withRel("GET"),
                            linkTo(methodOn(ContaController.class).createConta(null, null)).withRel("POST"),
                            linkTo(methodOn(ContaController.class).updateConta(contaResponse.id(), null)).withRel("PUT"),
                            linkTo(methodOn(ContaController.class).deleteConta(contaResponse.id())).withRel("DELETE"));

                }).collect(Collectors.toList());
        return new ResponseEntity<>(contaResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Cadastra uma conta e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
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

    @Operation(summary = "Atualiza uma conta e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Conta cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
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

    @Operation(summary = "Delete uma conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Conta não encontrada.",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping(value = ("/{idConta}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteConta(@PathVariable Long idConta) {

        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        contaRepository.delete(conta);

        return new ResponseEntity<>("Conta deletada com sucesso", HttpStatus.OK);
    }

}
