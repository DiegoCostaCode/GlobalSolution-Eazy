package com.example.eazy.controller;


import com.example.eazy.dto.usuario.UsuarioLoginDTO;
import com.example.eazy.dto.usuario.UsuarioRequestDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.mapper.UsuarioMapper;
import com.example.eazy.model.Usuario;
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
@RequestMapping(value = "/usuario", produces = {"application/json"})
public class UsuarioController {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioConvertido = usuarioMapper.requestToUsuario(usuarioRequestDTO);

        usuarioConvertido.setSenha(usuarioRequestDTO.senha());

        Usuario usuarioCriado = usuarioRepository.save(usuarioConvertido);
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.usuarioToResponse(usuarioCriado);

        return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@Valid @RequestBody UsuarioLoginDTO usuarioLoginDTO) {

        Usuario usuarioEncontrado = usuarioRepository.findByEmail(usuarioLoginDTO.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuarioEncontrado.verificarSenha(usuarioLoginDTO.senha())) {
            return new ResponseEntity<String>("Login falhou!",HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<String>("Login bem-sucedido", HttpStatus.OK);
    }

    @PutMapping(value = ("/{idUsuario}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable Long idUsuario, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
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

        return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUsuario(@PathVariable Long idUsuario) {
        Usuario usuarioEncontrado = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuarioEncontrado);

        return new ResponseEntity<>("Usuário deletado com sucesso!", HttpStatus.OK);
    }
}
