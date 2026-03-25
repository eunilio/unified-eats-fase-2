package br.com.fiap.unifiedeats2.cardapio.infra.web.json;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastrarItemCardapioRequestJson(
        @NotBlank(message = "nome não deve estar em branco")
        String nome,

        @NotBlank(message = "descricao não deve estar em branco")
        String descricao,

        @NotNull(message = "preco não deve ser nulo")
        @DecimalMin(value = "0.01", message = "preco deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "disponivelApenasNoLocal não deve ser nulo")
        Boolean disponivelApenasNoLocal,

        @NotBlank(message = "foto não deve estar em branco")
        String foto,

        @NotNull(message = "restauranteId não deve ser nulo")
        Long restauranteId
) {
}