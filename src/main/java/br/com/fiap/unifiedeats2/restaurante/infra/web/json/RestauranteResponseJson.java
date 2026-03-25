package br.com.fiap.unifiedeats2.restaurante.infra.web.json;

import br.com.fiap.unifiedeats2.compartilhado.infra.web.json.EnderecoRequestJson;

public record RestauranteResponseJson(
        Long id,
        String nome,
        EnderecoRequestJson endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {
}