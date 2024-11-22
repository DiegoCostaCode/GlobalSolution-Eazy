package com.example.eazy.controller;


import com.example.eazy.dto.usuario.*;
import com.example.eazy.mapper.InfoTributariasMapper;
import com.example.eazy.mapper.UsuarioMapper;
import com.example.eazy.model.InformacoesTributarias;
import com.example.eazy.model.Usuario;
import com.example.eazy.repository.InfoTributariaRepository;
import com.example.eazy.repository.UsuarioRepository;
import com.example.eazy.service.UsuarioService;
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

import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/usuario", produces = {"application/json"})
@Tag(name = "Usuario", description = "CRUD de usuario, e sistema de Login")
public class UsuarioController {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private InfoTributariasMapper infoTributariasMapper;
    @Autowired
    private InfoTributariaRepository infoTributariaRepository;

    @Operation(summary = "Trás um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado.",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<UsuarioResponseDTO>> findUserById(@PathVariable Long idUsuario) {
        Usuario usuarioEncontrado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.usuarioToResponse(usuarioEncontrado);

        return ResponseEntity.ok(
                EntityModel.of(usuarioResponseDTO,
                        linkTo(methodOn(UsuarioController.class).findUserById(idUsuario)).withSelfRel(),
                        linkTo(methodOn(UsuarioController.class).createUsuario(null)).withRel("POST"),
                        linkTo(methodOn(UsuarioController.class).updateUsuario(idUsuario, null)).withRel("PUT"),
                        linkTo(methodOn(UsuarioController.class).deleteUsuario(idUsuario)).withRel("DELETE")
                ));
    }

    @Operation(summary = "Cadastra um usuário e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastro com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {

        Optional<InformacoesTributarias> informacoesTributarias = infoTributariaRepository.findByEstado(usuarioRequestDTO.estado());

        if (informacoesTributarias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Usuario usuarioConvertido = usuarioMapper.requestToUsuario(usuarioRequestDTO);

        usuarioConvertido.setSenha(usuarioRequestDTO.senha());
        usuarioConvertido.setInformacoesTributarias(informacoesTributarias.get());

        Usuario usuarioCriado = usuarioRepository.save(usuarioConvertido);
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.usuarioToResponse(usuarioCriado);

        return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Login de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login feito com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado.",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "Senha incorreta.",
                    content = @Content(schema = @Schema())),

    })
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody UsuarioLoginDTO usuarioLoginDTO) {

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(usuarioLoginDTO.email());

        if (usuarioEncontrado.isEmpty()) {
            return new ResponseEntity<Map<String, String>>(Map.of("ERRO","Nenhum usuário bem-sucedido!"),HttpStatus.BAD_REQUEST);
        }

        if (!usuarioEncontrado.get().verificarSenha(usuarioLoginDTO.senha())) {
            return new ResponseEntity<Map<String, String>>(Map.of("ERRO","Senha incorreta!"),HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<Map<String, String>>(Map.of("SUCESS","Login bem-sucedido!"), HttpStatus.OK);
    }

    @Operation(summary = "Atualiza informações do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado.",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping(value = ("/{idUsuario}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> updateUsuario(@PathVariable Long idUsuario, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioEncontrado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioEncontrado.setUsuario(usuarioRequestDTO.usuario());
        usuarioEncontrado.setEmail(usuarioRequestDTO.email());
        usuarioEncontrado.setTelefone(usuarioRequestDTO.telefone());

        if (usuarioRequestDTO.senha() != null && !usuarioRequestDTO.senha().isEmpty()) {
            usuarioEncontrado.setSenha(usuarioRequestDTO.senha());
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioEncontrado);
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.usuarioToResponse(usuarioAtualizado);

        return new ResponseEntity<Map<String, String>>(Map.of("SUCESS","Usuário atualizado com sucesso!"), HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deleta um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado.",
                    content = @Content(schema = @Schema()))

    })
    @DeleteMapping(value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> deleteUsuario(@PathVariable Long idUsuario) {
        Usuario usuarioEncontrado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuarioEncontrado);

        return new ResponseEntity<Map<String, String>>(Map.of("SUCESS","Usuário deletado com sucesso!"), HttpStatus.OK);
    }

    @Operation(summary = "Obtém o consumo e valor total do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consumo e valor total obtidos com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado.",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/meu-consumo/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioConsumoTotalDTO> getConsumoEValorTotal(@PathVariable Long idUsuario) {
        UsuarioConsumoTotalDTO usuarioConsumoTotalDTO = usuarioService.consumoEValorTotal(idUsuario);
        return new ResponseEntity<>(usuarioConsumoTotalDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtém o menor consumo do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menor consumo obtido com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado.",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/meu-consumo/menor/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsumoMenorMaiorDTO> getMenorConsumo(@PathVariable Long idUsuario) {
        Usuario usuarioEncontrado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        ConsumoMenorMaiorDTO consumoMenor = usuarioService.menorConsumo(idUsuario);
        return new ResponseEntity<>(consumoMenor, HttpStatus.OK);
    }

    @Operation(summary = "Obtém o maior gasto do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maior gasto obtido com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado.",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/meu-consumo/maior/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsumoMenorMaiorDTO> getMaiorGasto(@PathVariable Long idUsuario) {
        Usuario usuarioEncontrado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        ConsumoMenorMaiorDTO consumoMaior = usuarioService.maiorConsumo(idUsuario);
        return new ResponseEntity<>(consumoMaior, HttpStatus.OK);
    }
}


