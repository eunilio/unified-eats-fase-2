package br.com.fiap.unifiedeats2.restaurante.infra.web.json;

import br.com.fiap.unifiedeats2.compartilhado.infra.web.json.EnderecoRequestJson;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestauranteRequestJson(
        @NotBlank String nome,
        @NotNull @Valid EnderecoRequestJson endereco,
        @NotBlank String tipoCozinha,
        @NotBlank String horarioFuncionamento,
        @NotNull Long donoId
) {
}