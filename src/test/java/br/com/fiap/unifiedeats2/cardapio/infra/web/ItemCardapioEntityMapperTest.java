package br.com.fiap.unifiedeats2.cardapio.infra.database.mapper;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.infra.database.entity.ItemCardapioEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioEntityMapperTest {

    @Test
    void deveMapearParaEntity() {
        ItemCardapio item = new ItemCardapio(
                1L,
                "Prato A",
                "Descricao A",
                BigDecimal.valueOf(39.90),
                true,
                "foto.jpg",
                2L
        );

        ItemCardapioEntity entity = ItemCardapioEntityMapper.toEntity(item);

        assertEquals(1L, entity.getId());
        assertEquals("Prato A", entity.getNome());
        assertEquals("Descricao A", entity.getDescricao());
        assertEquals(BigDecimal.valueOf(39.90), entity.getPreco());
        assertTrue(entity.getDisponivelApenasNoLocal());
        assertEquals("foto.jpg", entity.getFoto());
        assertEquals(2L, entity.getRestauranteId());
    }

    @Test
    void deveMapearParaDomain() {
        ItemCardapioEntity entity = new ItemCardapioEntity(
                1L,
                "Prato A",
                "Descricao A",
                BigDecimal.valueOf(39.90),
                true,
                "foto.jpg",
                2L
        );

        ItemCardapio domain = ItemCardapioEntityMapper.toDomain(entity);

        assertEquals(1L, domain.id());
        assertEquals("Prato A", domain.nome());
        assertEquals("Descricao A", domain.descricao());
        assertEquals(BigDecimal.valueOf(39.90), domain.preco());
        assertTrue(domain.disponivelApenasNoLocal());
        assertEquals("foto.jpg", domain.foto());
        assertEquals(2L, domain.restauranteId());
    }
}