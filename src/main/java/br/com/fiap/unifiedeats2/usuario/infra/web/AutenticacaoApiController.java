package br.com.fiap.unifiedeats2.usuario.infra.web;

import br.com.fiap.unifiedeats2.usuario.core.controller.AutenticarUsuarioController;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.AutenticarUsuarioRequestJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.fiap.unifiedeats2.docs.ApiExamples.*;

@RestController
@RequestMapping("/v1/autenticacao")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Operações de autenticação de usuários")
public class AutenticacaoApiController {

    private final AutenticarUsuarioController autenticarUsuarioController;

    @Operation(
            summary = "Realizar login",
            description = "Autentica um usuário com login e senha",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = LOGIN_REQUEST)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login realizado com sucesso",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutenticarUsuarioOutputDTO.class),
                                    examples = @ExampleObject(value = LOGIN_RESPONSE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = ERRO_VALIDACAO)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Login ou senha inválidos",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = ERRO_NAO_AUTORIZADO)
                            )
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AutenticarUsuarioOutputDTO> login(
            @Valid @RequestBody AutenticarUsuarioRequestJson json
    ) {
        AutenticarUsuarioInputDTO input = new AutenticarUsuarioInputDTO(
                json.login(),
                json.senha()
        );

        AutenticarUsuarioOutputDTO output = autenticarUsuarioController.autenticar(input);

        return ResponseEntity.ok(output);
    }
}