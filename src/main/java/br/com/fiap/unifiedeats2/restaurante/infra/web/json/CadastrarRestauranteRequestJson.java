package br.com.fiap.unifiedeats2.restaurante.infra.web.json;

import br.com.fiap.unifiedeats2.compartilhado.infra.web.json.EnderecoRequestJson;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarRestauranteRequestJson(
        @NotBlank(message = "nome não deve estar em branco")
        String nome,

        @NotNull(message = "endereco não deve ser nulo")
        @Valid
        EnderecoRequestJson endereco,

        @NotBlank(message = "tipoCozinha não deve estar em branco")
        String tipoCozinha,

        @NotBlank(message = "horarioFuncionamento não deve estar em branco")
        String horarioFuncionamento,

        @NotNull(message = "donoId não deve ser nulo")
        Long donoId
) {
}