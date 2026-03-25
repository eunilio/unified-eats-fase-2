package br.com.fiap.unifiedeats2.tipousuario.infra.web;

import br.com.fiap.unifiedeats2.docs.ApiExamples;
import br.com.fiap.unifiedeats2.tipousuario.core.controller.AtualizarTipoUsuarioController;
import br.com.fiap.unifiedeats2.tipousuario.core.controller.BuscarTipoUsuarioPorIdController;
import br.com.fiap.unifiedeats2.tipousuario.core.controller.CadastrarTipoUsuarioController;
import br.com.fiap.unifiedeats2.tipousuario.core.controller.ListarTiposUsuarioController;
import br.com.fiap.unifiedeats2.tipousuario.core.controller.RemoverTipoUsuarioController;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.AtualizarTipoUsuarioInputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.CadastrarTipoUsuarioInputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.infra.web.json.AtualizarTipoUsuarioRequestJson;
import br.com.fiap.unifiedeats2.tipousuario.infra.web.json.CadastrarTipoUsuarioRequestJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/tipos-usuario")
@RequiredArgsConstructor
@Tag(name = "Tipos de Usuário", description = "Endpoints para gerenciamento de tipos de usuário")
public class TipoUsuarioApiController {

    private final CadastrarTipoUsuarioController cadastrarTipoUsuarioController;
    private final BuscarTipoUsuarioPorIdController buscarTipoUsuarioPorIdController;
    private final ListarTiposUsuarioController listarTiposUsuarioController;
    private final AtualizarTipoUsuarioController atualizarTipoUsuarioController;
    private final RemoverTipoUsuarioController removerTipoUsuarioController;

    @Operation(
            summary = "Cadastrar tipo de usuário",
            description = "Cadastra um novo tipo de usuário no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de usuário cadastrado com sucesso"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_VALIDACAO)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Void> cadastrar(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para cadastro do tipo de usuário",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.TIPO_USUARIO_REQUEST)
                    )
            )
            @RequestBody CadastrarTipoUsuarioRequestJson json
    ) {
        TipoUsuarioOutputDTO output = cadastrarTipoUsuarioController.cadastrar(
                new CadastrarTipoUsuarioInputDTO(json.nome())
        );

        return ResponseEntity.created(URI.create("/v1/tipos-usuario/" + output.id())).build();
    }

    @Operation(
            summary = "Listar tipos de usuário",
            description = "Retorna todos os tipos de usuário cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Lista de tipos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<TipoUsuarioOutputDTO>> listar() {
        return ResponseEntity.ok(listarTiposUsuarioController.listar());
    }

    @Operation(
            summary = "Buscar tipo de usuário por ID",
            description = "Retorna os dados de um tipo de usuário específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tipo de usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_RECURSO_NAO_ENCONTRADO)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioOutputDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarTipoUsuarioPorIdController.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar tipo de usuário",
            description = "Atualiza os dados de um tipo de usuário existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de usuário atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_VALIDACAO)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tipo de usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_RECURSO_NAO_ENCONTRADO)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para atualização do tipo de usuário",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.TIPO_USUARIO_REQUEST)
                    )
            )
            @RequestBody AtualizarTipoUsuarioRequestJson json
    ) {
        atualizarTipoUsuarioController.atualizar(
                new AtualizarTipoUsuarioInputDTO(id, json.nome())
        );

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Remover tipo de usuário",
            description = "Remove um tipo de usuário pelo identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de usuário removido com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tipo de usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_RECURSO_NAO_ENCONTRADO)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerTipoUsuarioController.remover(id);
        return ResponseEntity.noContent().build();
    }
}