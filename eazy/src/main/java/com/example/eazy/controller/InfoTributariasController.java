package com.example.eazy.controller;

import com.example.eazy.dto.informacoesTributariasDTO.InfoTribuRequestDTO;
import com.example.eazy.dto.informacoesTributariasDTO.InfoTribuResponseDTO;
import com.example.eazy.dto.usuario.UsuarioLoginDTO;
import com.example.eazy.dto.usuario.UsuarioRequestDTO;
import com.example.eazy.dto.usuario.UsuarioResponseDTO;
import com.example.eazy.mapper.InfoTributariasMapper;
import com.example.eazy.model.Enum_estado;
import com.example.eazy.model.InformacoesTributarias;
import com.example.eazy.model.Usuario;
import com.example.eazy.repository.InfoTributariaRepository;
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
@RequestMapping(value = "/infoTriburaria", produces = {"application/json"})
@Tag(name = "Informações Tributárias", description = "CRUD de informações tributárias")
public class InfoTributariasController {

    @Autowired
    private InfoTributariaRepository infoTributariaRepository;

    @Autowired
    private InfoTributariasMapper infoTributariasMapper;


    @Operation(summary = "Trás uma informação tributária pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informação tributária encontrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Informação tributária não encontrada.",
                    content = @Content(schema = @Schema()))

    })
    @GetMapping(value = "/{idInfosTribu}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<InfoTribuResponseDTO>> findInformacoesById(@PathVariable Long idInfosTribu) {
        InformacoesTributarias infoTributariaEncontrada = infoTributariaRepository.findById(idInfosTribu)
                .orElseThrow(() -> new RuntimeException("Informação tributária não encontrada"));

        InfoTribuResponseDTO infoTribuResponseDTO = infoTributariasMapper.infoTributariasResponseDTO(infoTributariaEncontrada);

        return ResponseEntity.ok(
                EntityModel.of(infoTribuResponseDTO,
                        linkTo(methodOn(InfoTributariasController.class).findInformacoesById(idInfosTribu)).withSelfRel(),
                        linkTo(methodOn(InfoTributariasController.class).findInformacoesByEstado(infoTribuResponseDTO.estado())).withSelfRel(),
                        linkTo(methodOn(InfoTributariasController.class).showInfosTributarias()).withRel("GET"),
                        linkTo(methodOn(InfoTributariasController.class).createInfosTributaria(null)).withRel("POST"),
                        linkTo(methodOn(InfoTributariasController.class).updateInfosTributaria(idInfosTribu, null)).withRel("PUT"),
                        linkTo(methodOn(InfoTributariasController.class).deleteInfoTributaria(idInfosTribu)).withRel("DELETE")
                ));
    }

    @Operation(summary = "Trás uma informação tributária pelo estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informação tributária encontrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Informação tributária não encontrada.",
                    content = @Content(schema = @Schema()))

    })
    @GetMapping(value = "/estado/{estado}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<InfoTribuResponseDTO>> findInformacoesByEstado(@PathVariable Enum_estado estado) {
        InformacoesTributarias infoTributariaEncontrada = infoTributariaRepository.findByEstado(estado)
                .orElseThrow(() -> new RuntimeException("Informação tributária não encontrada"));

        InfoTribuResponseDTO infoTribuResponseDTO = infoTributariasMapper.infoTributariasResponseDTO(infoTributariaEncontrada);

        return ResponseEntity.ok(
                EntityModel.of(infoTribuResponseDTO,
                        linkTo(methodOn(InfoTributariasController.class).findInformacoesByEstado(estado)).withSelfRel(),
                        linkTo(methodOn(InfoTributariasController.class).findInformacoesById(infoTribuResponseDTO.id())).withRel("GET"),
                        linkTo(methodOn(InfoTributariasController.class).showInfosTributarias()).withRel("GET"),
                        linkTo(methodOn(InfoTributariasController.class).createInfosTributaria(null)).withRel("POST"),
                        linkTo(methodOn(InfoTributariasController.class).updateInfosTributaria(infoTribuResponseDTO.id(), null)).withRel("PUT"),
                        linkTo(methodOn(InfoTributariasController.class).deleteInfoTributaria(infoTribuResponseDTO.id())).withRel("DELETE")
                ));
    }

    @Operation(summary = "Trás todas as informações tributárias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações tributárias encontradas com sucesso!"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<InfoTribuResponseDTO>>> showInfosTributarias() {

    List<InformacoesTributarias> infoTributarias = infoTributariaRepository.findAll();

    List<EntityModel<InfoTribuResponseDTO>> infoTributariasResponse = infoTributarias.stream()
            .map(informacoesTributarias -> {
                InfoTribuResponseDTO infoTributariaResponse = infoTributariasMapper.infoTributariasResponseDTO(informacoesTributarias);
                return EntityModel.of(infoTributariaResponse,
                        linkTo(methodOn(InfoTributariasController.class).showInfosTributarias()).withSelfRel(),
                        linkTo(methodOn(InfoTributariasController.class).findInformacoesById(infoTributariaResponse.id())).withRel("GET"),
                        linkTo(methodOn(InfoTributariasController.class).findInformacoesByEstado(infoTributariaResponse.estado())).withRel("GET"),
                        linkTo(methodOn(InfoTributariasController.class).createInfosTributaria(null)).withRel("POST"),
                        linkTo(methodOn(InfoTributariasController.class).deleteInfoTributaria(informacoesTributarias.getId())).withRel("DELETE"),
                        linkTo(methodOn(InfoTributariasController.class).updateInfosTributaria(informacoesTributarias.getId(), null)).withRel("update"));
            })
            .collect(Collectors.toList());
    return new ResponseEntity<>(infoTributariasResponse, HttpStatus.OK);
}

    @Operation(summary = "Cadastra informações tributárias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Informações tributárias encontradas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Informações tributárias não encontrada.",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfoTribuResponseDTO> createInfosTributaria(@Valid @RequestBody InfoTribuRequestDTO infoTribuRequestDTO) {

        InformacoesTributarias infosTribuConvertidas = infoTributariasMapper.requestToInfoTributarias(infoTribuRequestDTO);

        InformacoesTributarias infosCriadas = infoTributariaRepository.save(infosTribuConvertidas);

        InfoTribuResponseDTO infoTribuResponseDTO = infoTributariasMapper.infoTributariasResponseDTO(infosCriadas);

        return new ResponseEntity<>(infoTribuResponseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza informações tributárias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Informações tributárias atualizadas com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Informações tributárias não encontrada.",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping(value = ("/{idInfosTribu}"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfoTribuResponseDTO> updateInfosTributaria(@PathVariable Long idInfosTribu, @Valid @RequestBody InfoTribuRequestDTO infoTribuRequestDTO) {

       InformacoesTributarias infoTribuEncontrada = infoTributariaRepository.findById(idInfosTribu)
                .orElseThrow(() -> new RuntimeException("Informação Tributária não encontrada"));

        infoTribuEncontrada.setEstado(infoTribuRequestDTO.estado());
        infoTribuEncontrada.setValorKwh(infoTribuRequestDTO.valorKwh());

        InformacoesTributarias informacoesAtualizado = infoTributariaRepository.save(infoTribuEncontrada);
        InfoTribuResponseDTO infoTribuResponseDTO = infoTributariasMapper.infoTributariasResponseDTO(informacoesAtualizado);

        return new ResponseEntity<>(infoTribuResponseDTO, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deleta informações tributárias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações tributárias deletada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Informações tributárias não encontrada.",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping(value = "/{idInfosTribu}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteInfoTributaria(@PathVariable Long idInfosTribu) {

        InformacoesTributarias infoTribuEncontrada = infoTributariaRepository.findById(idInfosTribu)
                .orElseThrow(() -> new RuntimeException("Informação Tributária não encontrada"));

        infoTributariaRepository.delete(infoTribuEncontrada);

        return new ResponseEntity<>("Informações tributárias deletada com sucesso!", HttpStatus.OK);
    }
}
