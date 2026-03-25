package br.com.fiap.unifiedeats2.usuario.infra.web;

import br.com.fiap.unifiedeats2.docs.ApiExamples;
import br.com.fiap.unifiedeats2.tipousuario.infra.web.json.TipoUsuarioRefJson;
import br.com.fiap.unifiedeats2.usuario.core.controller.*;
import br.com.fiap.unifiedeats2.usuario.core.dto.AlterarSenhaUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.AtualizarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.mapper.AtualizarUsuarioInputMapper;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.AlterarSenhaRequestJson;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.AtualizarUsuarioRequestJson;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.CadastrarUsuarioRequestJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static br.com.fiap.unifiedeats2.docs.ApiExamples.*;

@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioApiController {

    private final CadastrarUsuarioController cadastrarUsuarioController;
    private final BuscarUsuarioPorIdController buscarUsuarioPorIdController;
    private final ListarUsuariosController listarUsuariosController;
    private final BuscarUsuariosPorNomeController buscarUsuariosPorNomeController;
    private final AtualizarUsuarioController atualizarUsuarioController;
    private final RemoverUsuarioController removerUsuarioController;
    private final AlterarSenhaUsuarioController alterarSenhaUsuarioController;

    @Operation(summary = "Listar usuários")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<UsuarioOutputDTO>> listar() {
        return ResponseEntity.ok(listarUsuariosController.listar());
    }

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado",
            content = @Content(examples = @ExampleObject(value = ERRO_RECURSO_NAO_ENCONTRADO))
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioOutputDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarUsuarioPorIdController.buscarPorId(id));
    }

    @Operation(summary = "Buscar usuários por nome")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioOutputDTO>> buscarPorNome(
            @Parameter(description = "Nome para busca", example = "joão")
            @RequestParam String nome
    ) {
        return ResponseEntity.ok(buscarUsuariosPorNomeController.buscarPorNome(nome));
    }

    @Operation(
            summary = "Cadastrar usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = @ExampleObject(value = USUARIO_REQUEST))
            )
    )
    @ApiResponse(responseCode = "201", description = "Usuário criado")
    @ApiResponse(
            responseCode = "400",
            description = "Erro de validação",
            content = @Content(examples = @ExampleObject(value = ERRO_VALIDACAO))
    )
    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody CadastrarUsuarioRequestJson json) {
        Long id = cadastrarUsuarioController.cadastrar(mapToDto(json));
        return ResponseEntity.created(URI.create("/v1/usuarios/" + id)).build();
    }

    @Operation(
            summary = "Atualizar usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = @ExampleObject(value = USUARIO_REQUEST))
            )
    )
    @ApiResponse(responseCode = "204", description = "Usuário atualizado")
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarUsuarioRequestJson json
    ) {
        AtualizarUsuarioInputDTO input = AtualizarUsuarioInputMapper.toInput(id, json);
        atualizarUsuarioController.atualizar(input);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Alterar senha",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(value = """
                                {
                                  "senhaAtual": "123456",
                                  "novaSenha": "654321"
                                }
                                """)
                    )
            )
    )
    @ApiResponse(responseCode = "204", description = "Senha alterada")
    @ApiResponse(
            responseCode = "401",
            description = "Senha inválida",
            content = @Content(examples = @ExampleObject(value = ERRO_NAO_AUTORIZADO))
    )
    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @Valid @RequestBody AlterarSenhaRequestJson json
    ) {
        AlterarSenhaUsuarioInputDTO input = new AlterarSenhaUsuarioInputDTO(
                id,
                json.senhaAtual(),
                json.novaSenha()
        );

        alterarSenhaUsuarioController.alterarSenha(input);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remover usuário")
    @ApiResponse(responseCode = "204", description = "Usuário removido")
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado",
            content = @Content(examples = @ExampleObject(value = ERRO_RECURSO_NAO_ENCONTRADO))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        removerUsuarioController.remover(id);
        return ResponseEntity.noContent().build();
    }

    private CadastrarUsuarioInputDTO mapToDto(CadastrarUsuarioRequestJson json) {

        List<Long> tiposUsuarioIds = json.tipoUsuarios() == null
                ? List.of()
                : json.tipoUsuarios().stream()
                .map(TipoUsuarioRefJson::id)
                .filter(Objects::nonNull)
                .toList();

        return new CadastrarUsuarioInputDTO(
                json.nome(),
                json.email(),
                json.login(),
                json.senha(),
                json.endereco().cep(),
                json.endereco().logradouro(),
                json.endereco().numero(),
                json.endereco().complemento(),
                json.endereco().bairro(),
                json.endereco().cidade(),
                json.endereco().estado(),
                tiposUsuarioIds
        );
    }
}