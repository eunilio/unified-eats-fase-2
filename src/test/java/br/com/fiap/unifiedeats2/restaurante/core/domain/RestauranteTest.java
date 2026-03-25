package br.com.fiap.unifiedeats2.restaurante.core.domain;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    @Test
    void deveCriarRestauranteValido() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante Legal", endereco(), "Brasileira", "10h às 22h", 5L);

        assertEquals(1L, restaurante.id());
        assertEquals("Restaurante Legal", restaurante.nome());
        assertEquals("Brasileira", restaurante.tipoCozinha());
        assertEquals("10h às 22h", restaurante.horarioFuncionamento());
        assertEquals(5L, restaurante.donoId());
    }

    @Test
    void deveLancarExcecaoQuandoCamposObrigatoriosForemInvalidos() {
        assertEquals("Nome do restaurante é obrigatório.", assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, " ", endereco(), "Brasileira", "10h às 22h", 5L)).getMessage());

        assertEquals("Nome do restaurante deve ter pelo menos 3 caracteres.", assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, "AB", endereco(), "Brasileira", "10h às 22h", 5L)).getMessage());

        assertEquals("Endereço do restaurante é obrigatório.", assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, "Restaurante", null, "Brasileira", "10h às 22h", 5L)).getMessage());

        assertEquals("Tipo de cozinha é obrigatório.", assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, "Restaurante", endereco(), " ", "10h às 22h", 5L)).getMessage());

        assertEquals("Horário de funcionamento é obrigatório.", assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, "Restaurante", endereco(), "Brasileira", " ", 5L)).getMessage());

        assertEquals("Dono do restaurante é obrigatório.", assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, "Restaurante", endereco(), "Brasileira", "10h às 22h", null)).getMessage());
    }

    private Endereco endereco() {
        return new Endereco("03450000", "Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP");
    }
}
