package br.com.fiap.unifiedeats2.compartilhado.infra.web.json;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestJson(
        @NotBlank String cep,
        @NotBlank String logradouro,
        @NotBlank String numero,
        String complemento,
        @NotBlank String bairro,
        @NotBlank String cidade,
        @NotBlank String estado
) {
}