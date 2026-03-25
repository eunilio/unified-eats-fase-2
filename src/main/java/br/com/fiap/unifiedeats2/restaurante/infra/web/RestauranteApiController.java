package br.com.fiap.unifiedeats2.restaurante.infra.web;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.docs.ApiExamples;
import br.com.fiap.unifiedeats2.restaurante.core.controller.AtualizarRestauranteController;
import br.com.fiap.unifiedeats2.restaurante.core.controller.BuscarRestaurantePorIdController;
import br.com.fiap.unifiedeats2.restaurante.core.controller.CadastrarRestauranteController;
import br.com.fiap.unifiedeats2.restaurante.core.controller.ListarRestaurantesController;
import br.com.fiap.unifiedeats2.restaurante.core.controller.RemoverRestauranteController;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.mapper.AtualizarRestauranteInputMapper;
import br.com.fiap.unifiedeats2.restaurante.infra.web.json.AtualizarRestauranteRequestJson;
import br.com.fiap.unifiedeats2.restaurante.infra.web.json.CadastrarRestauranteRequestJson;
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
@RequestMapping("/v1/restaurantes")
@RequiredArgsConstructor
@Tag(name = "Restaurantes", description = "Endpoints para gerenciamento de restaurantes")
public class RestauranteApiController {

    private final CadastrarRestauranteController cadastrarRestauranteController;
    private final BuscarRestaurantePorIdController buscarRestaurantePorIdController;
    private final ListarRestaurantesController listarRestaurantesController;
    private final AtualizarRestauranteController atualizarRestauranteController;
    private final RemoverRestauranteController removerRestauranteController;

    @Operation(
            summary = "Cadastrar restaurante",
            description = "Cadastra um novo restaurante vinculado a um usuário dono existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurante cadastrado com sucesso"),
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
                    description = "Usuário dono não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_RECURSO_NAO_ENCONTRADO)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Void> cadastrar(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para cadastro do restaurante",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.RESTAURANTE_REQUEST)
                    )
            )
            @RequestBody CadastrarRestauranteRequestJson json
    ) {
        CadastrarRestauranteOutputDTO output = cadastrarRestauranteController.cadastrar(
                new CadastrarRestauranteInputDTO(
                        json.nome(),
                        new Endereco(
                                json.endereco().cep(),
                                json.endereco().logradouro(),
                                json.endereco().numero(),
                                json.endereco().complemento(),
                                json.endereco().bairro(),
                                json.endereco().cidade(),
                                json.endereco().estado()
                        ),
                        json.tipoCozinha(),
                        json.horarioFuncionamento(),
                        json.donoId()
                )
        );

        return ResponseEntity.created(URI.create("/v1/restaurantes/" + output.id())).build();
    }

    @Operation(
            summary = "Listar restaurantes",
            description = "Retorna todos os restaurantes cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<RestauranteOutputDTO>> listar() {
        return ResponseEntity.ok(listarRestaurantesController.listar());
    }

    @Operation(
            summary = "Buscar restaurante por ID",
            description = "Retorna os dados de um restaurante específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurante encontrado com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_RECURSO_NAO_ENCONTRADO)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteOutputDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarRestaurantePorIdController.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar restaurante",
            description = "Atualiza os dados de um restaurante existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurante atualizado com sucesso"),
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
                    description = "Restaurante ou usuário dono não encontrado",
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
                    description = "Dados para atualização do restaurante",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.RESTAURANTE_REQUEST)
                    )
            )
            @RequestBody AtualizarRestauranteRequestJson json
    ) {
        atualizarRestauranteController.atualizar(
                AtualizarRestauranteInputMapper.toInput(id, json)
        );
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Remover restaurante",
            description = "Remove um restaurante pelo identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurante removido com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_RECURSO_NAO_ENCONTRADO)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerRestauranteController.remover(id);
        return ResponseEntity.noContent().build();
    }
}