package br.com.fiap.unifiedeats2.cardapio.core.dto;

import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;

import java.math.BigDecimal;

public record ItemCardapioOutputDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivelApenasNoLocal,
        String foto,
        RestauranteOutputDTO restaurante
) {
}