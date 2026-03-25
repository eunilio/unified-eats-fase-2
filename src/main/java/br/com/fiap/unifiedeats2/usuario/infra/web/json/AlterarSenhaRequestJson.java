package br.com.fiap.unifiedeats2.usuario.infra.web.json;

import jakarta.validation.constraints.NotBlank;

public record AlterarSenhaRequestJson(
        @NotBlank String senhaAtual,
        @NotBlank String novaSenha
) {
}