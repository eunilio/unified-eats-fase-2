package br.com.fiap.unifiedeats2.usuario.infra.web.json;

import br.com.fiap.unifiedeats2.compartilhado.infra.web.json.EnderecoRequestJson;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AtualizarUsuarioRequestJson(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String login,
        @Valid EnderecoRequestJson endereco
) {
}