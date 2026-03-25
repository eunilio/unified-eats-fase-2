package br.com.fiap.unifiedeats2.cardapio.core.dto;

import java.math.BigDecimal;

public record CadastrarItemCardapioOutputDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivelApenasNoLocal,
        String foto,
        Long restauranteId
) {
}