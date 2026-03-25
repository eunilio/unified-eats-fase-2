package br.com.fiap.unifiedeats2.cardapio.core.domain;

import java.math.BigDecimal;

public record ItemCardapio(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponivelApenasNoLocal,
        String foto,
        Long restauranteId
) {

    public ItemCardapio {
        validarNome(nome);
        validarDescricao(descricao);
        validarPreco(preco);
        validarDisponibilidade(disponivelApenasNoLocal);
        validarRestaurante(restauranteId);
    }

    private static void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do item do cardápio é obrigatório.");
        }

        if (nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome do item do cardápio deve ter pelo menos 3 caracteres.");
        }
    }

    private static void validarDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição do item do cardápio é obrigatória.");
        }
    }

    private static void validarPreco(BigDecimal preco) {
        if (preco == null) {
            throw new IllegalArgumentException("Preço do item do cardápio é obrigatório.");
        }

        if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço do item do cardápio deve ser maior que zero.");
        }
    }

    private static void validarDisponibilidade(Boolean disponivelApenasNoLocal) {
        if (disponivelApenasNoLocal == null) {
            throw new IllegalArgumentException("Disponibilidade do item do cardápio é obrigatória.");
        }
    }

    private static void validarRestaurante(Long restauranteId) {
        if (restauranteId == null) {
            throw new IllegalArgumentException("Restaurante do item do cardápio é obrigatório.");
        }
    }
}