package br.com.fiap.unifiedeats2.cardapio.infra.web;

import br.com.fiap.unifiedeats2.cardapio.core.controller.AtualizarItemCardapioController;
import br.com.fiap.unifiedeats2.cardapio.core.controller.BuscarItemCardapioPorIdController;
import br.com.fiap.unifiedeats2.cardapio.core.controller.CadastrarItemCardapioController;
import br.com.fiap.unifiedeats2.cardapio.core.controller.ListarItensCardapioController;
import br.com.fiap.unifiedeats2.cardapio.core.controller.RemoverItemCardapioController;
import br.com.fiap.unifiedeats2.cardapio.core.dto.AtualizarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.infra.web.json.AtualizarItemCardapioRequestJson;
import br.com.fiap.unifiedeats2.cardapio.infra.web.json.CadastrarItemCardapioRequestJson;
import br.com.fiap.unifiedeats2.docs.ApiExamples;
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
@RequestMapping("/v1/itens-cardapio")
@RequiredArgsConstructor
@Tag(name = "Itens de Cardápio", description = "Endpoints para gerenciamento dos itens de cardápio dos restaurantes")
public class ItemCardapioApiController {

    private final CadastrarItemCardapioController cadastrarItemCardapioController;
    private final BuscarItemCardapioPorIdController buscarItemCardapioPorIdController;
    private final ListarItensCardapioController listarItensCardapioController;
    private final AtualizarItemCardapioController atualizarItemCardapioController;
    private final RemoverItemCardapioController removerItemCardapioController;

    @Operation(
            summary = "Cadastrar item de cardápio",
            description = "Cadastra um novo item de cardápio vinculado a um restaurante existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item de cardápio cadastrado com sucesso"),
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
                    description = "Restaurante não encontrado",
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
                    description = "Dados para cadastro do item de cardápio",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.CADASTRAR_ITEM_CARDAPIO_REQUEST)
                    )
            )
            @RequestBody CadastrarItemCardapioRequestJson json
    ) {
        CadastrarItemCardapioOutputDTO output = cadastrarItemCardapioController.cadastrar(
                new CadastrarItemCardapioInputDTO(
                        json.nome(),
                        json.descricao(),
                        json.preco(),
                        json.disponivelApenasNoLocal(),
                        json.foto(),
                        json.restauranteId()
                )
        );

        return ResponseEntity.created(URI.create("/v1/itens-cardapio/" + output.id())).build();
    }

    @Operation(
            summary = "Listar itens de cardápio",
            description = "Retorna todos os itens de cardápio cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Lista de itens retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ItemCardapioOutputDTO>> listar() {
        return ResponseEntity.ok(listarItensCardapioController.listar());
    }

    @Operation(
            summary = "Buscar item de cardápio por ID",
            description = "Retorna os dados de um item de cardápio específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item de cardápio não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_RECURSO_NAO_ENCONTRADO)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioOutputDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarItemCardapioPorIdController.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar item de cardápio",
            description = "Atualiza os dados de um item de cardápio existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item de cardápio atualizado com sucesso"),
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
                    description = "Item de cardápio ou restaurante não encontrado",
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
                    description = "Dados para atualização do item de cardápio",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ATUALIZAR_ITEM_CARDAPIO_REQUEST)
                    )
            )
            @RequestBody AtualizarItemCardapioRequestJson json
    ) {
        atualizarItemCardapioController.atualizar(
                new AtualizarItemCardapioInputDTO(
                        id,
                        json.nome(),
                        json.descricao(),
                        json.preco(),
                        json.disponivelApenasNoLocal(),
                        json.foto(),
                        json.restauranteId()
                )
        );

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Remover item de cardápio",
            description = "Remove um item de cardápio pelo identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item de cardápio removido com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item de cardápio não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = ApiExamples.ERRO_RECURSO_NAO_ENCONTRADO)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerItemCardapioController.remover(id);
        return ResponseEntity.noContent().build();
    }
}