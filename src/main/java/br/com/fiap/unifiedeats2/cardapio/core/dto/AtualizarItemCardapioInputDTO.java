package br.com.fiap.unifiedeats2.cardapio.core.dto;

import java.math.BigDecimal;

public record AtualizarItemCardapioInputDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivelApenasNoLocal,
        String foto,
        Long restauranteId
) {
}