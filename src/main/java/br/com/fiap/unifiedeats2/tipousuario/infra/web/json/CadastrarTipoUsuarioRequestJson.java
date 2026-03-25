package br.com.fiap.unifiedeats2.tipousuario.infra.web.json;

import jakarta.validation.constraints.NotBlank;

public record CadastrarTipoUsuarioRequestJson(
        @NotBlank String nome
) {
}