package br.com.fiap.unifiedeats2.restaurante.core.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.compartilhado.infra.web.json.EnderecoRequestJson;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.dto.AtualizarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.infra.web.json.AtualizarRestauranteRequestJson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteMappersTest {

    @Test
    void deveMapearAtualizarRestauranteInput() {
        AtualizarRestauranteRequestJson json = new AtualizarRestauranteRequestJson(
                "Restaurante",
                new EnderecoRequestJson("03450000", "Rua A", "10", "Apto", "Centro", "São Paulo", "sp"),
                "Brasileira",
                "10h às 22h",
                9L
        );

        AtualizarRestauranteInputDTO input = AtualizarRestauranteInputMapper.toInput(5L, json);

        assertEquals(5L, input.id());
        assertEquals("SP", input.endereco().estado());
    }

    @Test
    void deveMapearRestauranteParaOutput() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante", new Endereco("03450000", "Rua A", "10", "Apto", "Centro", "São Paulo", "SP"), "Brasileira", "10h às 22h", 9L);

        RestauranteOutputDTO output = RestauranteOutputMapper.paraOutput(restaurante);

        assertEquals(1L, output.id());
        assertEquals("Restaurante", output.nome());
        assertEquals("SP", output.endereco().estado());
    }
}
