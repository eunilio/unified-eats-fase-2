package br.com.fiap.unifiedeats2.cardapio.core.mapper;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioOutputMapperTest {

    @Test
    void deveMapearItemCardapioParaOutput() {
        ItemCardapio item = new ItemCardapio(1L, "Hambúrguer", "Pão e carne", new BigDecimal("29.90"), true, "foto.jpg", 10L);

        Restaurante restaurante = new Restaurante(10L, "Restaurante", new Endereco("03450000", "Rua A", "10", "Apto", "Centro", "São Paulo", "SP"), "Brasileira", "10h às 22h", 1L);

        CadastrarItemCardapioOutputDTO cadastrarOutput = ItemCardapioOutputMapper.paraCadastrarOutput(item);
        ItemCardapioOutputDTO output = ItemCardapioOutputMapper.paraOutput(item, restaurante);

        assertEquals(1L, cadastrarOutput.id());
        assertEquals(1L, output.id());
        assertEquals("Hambúrguer", output.nome());
        assertEquals(new BigDecimal("29.90"), output.preco());
        assertTrue(output.disponivelApenasNoLocal());
        assertEquals(10L, output.restaurante().id());
    }
}
