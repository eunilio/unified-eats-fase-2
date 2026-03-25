package br.com.fiap.unifiedeats2.cardapio.core.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioTest {

    @Test
    void deveCriarItemCardapioValido() {
        ItemCardapio item = new ItemCardapio(1L, "Hambúrguer", "Pão e carne", new BigDecimal("29.90"), true, "foto.jpg", 10L);

        assertEquals(1L, item.id());
        assertEquals("Hambúrguer", item.nome());
        assertEquals(new BigDecimal("29.90"), item.preco());
        assertTrue(item.disponivelApenasNoLocal());
        assertEquals(10L, item.restauranteId());
    }

    @Test
    void deveLancarExcecaoQuandoCamposObrigatoriosForemInvalidos() {
        assertEquals("Nome do item do cardápio é obrigatório.", assertThrows(IllegalArgumentException.class,
                () -> new ItemCardapio(1L, " ", "Pão e carne", new BigDecimal("29.90"), true, "foto.jpg", 10L)).getMessage());

        assertEquals("Nome do item do cardápio deve ter pelo menos 3 caracteres.", assertThrows(IllegalArgumentException.class,
                () -> new ItemCardapio(1L, "AB", "Pão e carne", new BigDecimal("29.90"), true, "foto.jpg", 10L)).getMessage());

        assertEquals("Descrição do item do cardápio é obrigatória.", assertThrows(IllegalArgumentException.class,
                () -> new ItemCardapio(1L, "Hambúrguer", " ", new BigDecimal("29.90"), true, "foto.jpg", 10L)).getMessage());

        assertEquals("Preço do item do cardápio é obrigatório.", assertThrows(IllegalArgumentException.class,
                () -> new ItemCardapio(1L, "Hambúrguer", "Pão e carne", null, true, "foto.jpg", 10L)).getMessage());

        assertEquals("Preço do item do cardápio deve ser maior que zero.", assertThrows(IllegalArgumentException.class,
                () -> new ItemCardapio(1L, "Hambúrguer", "Pão e carne", BigDecimal.ZERO, true, "foto.jpg", 10L)).getMessage());

        assertEquals("Disponibilidade do item do cardápio é obrigatória.", assertThrows(IllegalArgumentException.class,
                () -> new ItemCardapio(1L, "Hambúrguer", "Pão e carne", new BigDecimal("29.90"), null, "foto.jpg", 10L)).getMessage());

        assertEquals("Restaurante do item do cardápio é obrigatório.", assertThrows(IllegalArgumentException.class,
                () -> new ItemCardapio(1L, "Hambúrguer", "Pão e carne", new BigDecimal("29.90"), true, "foto.jpg", null)).getMessage());
    }
}
