package br.com.fiap.unifiedeats2.restaurante.infra.database.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.infra.database.entity.RestauranteEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteEntityMapperTest {

    @Test
    void deveMapearParaEntity() {
        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante A",
                new Endereco("03450000", "Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP"),
                "Brasileira",
                "09:00 às 18:00",
                2L
        );

        RestauranteEntity entity = RestauranteEntityMapper.toEntity(restaurante);

        assertEquals(1L, entity.getId());
        assertEquals("Restaurante A", entity.getNome());
        assertEquals("03450000", entity.getCep());
        assertEquals("Brasileira", entity.getTipoCozinha());
        assertEquals("09:00 às 18:00", entity.getHorarioFuncionamento());
        assertEquals(2L, entity.getDonoId());
    }

    @Test
    void deveMapearParaDomain() {
        RestauranteEntity entity = new RestauranteEntity(
                1L,
                "Restaurante A",
                "03450000",
                "Rua A",
                "10",
                "Apto 1",
                "Centro",
                "São Paulo",
                "SP",
                "Brasileira",
                "09:00 às 18:00",
                2L
        );

        Restaurante domain = RestauranteEntityMapper.toDomain(entity);

        assertEquals(1L, domain.id());
        assertEquals("Restaurante A", domain.nome());
        assertEquals("03450000", domain.endereco().cep());
        assertEquals("Brasileira", domain.tipoCozinha());
        assertEquals("09:00 às 18:00", domain.horarioFuncionamento());
        assertEquals(2L, domain.donoId());
    }
}