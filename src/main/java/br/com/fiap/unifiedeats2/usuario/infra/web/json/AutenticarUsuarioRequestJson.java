package br.com.fiap.unifiedeats2.usuario.infra.web.json;

import jakarta.validation.constraints.NotBlank;

public record AutenticarUsuarioRequestJson(
        @NotBlank String login,
        @NotBlank String senha
) {
}