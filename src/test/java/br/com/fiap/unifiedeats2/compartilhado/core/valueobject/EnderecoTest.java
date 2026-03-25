package br.com.fiap.unifiedeats2.compartilhado.core.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveCriarEnderecoComCamposFormatadosEEstadoMaiusculo() {
        Endereco endereco = new Endereco(" 03450000 ", " Rua A ", " 10 ", " apto 1 ", " Centro ", " São Paulo ", " sp ");

        assertEquals("03450000", endereco.cep());
        assertEquals("Rua A", endereco.logradouro());
        assertEquals("10", endereco.numero());
        assertEquals("apto 1", endereco.complemento());
        assertEquals("Centro", endereco.bairro());
        assertEquals("São Paulo", endereco.cidade());
        assertEquals("SP", endereco.estado());
    }

    @Test
    void devePermitirComplementoNulo() {
        Endereco endereco = new Endereco("03450000", "Rua A", "10", null, "Centro", "São Paulo", "SP");

        assertNull(endereco.complemento());
    }

    @Test
    void deveLancarExcecaoQuandoCepForInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Endereco(" ", "Rua A", "10", null, "Centro", "São Paulo", "SP"));

        assertEquals("CEP inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoLogradouroForInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Endereco("03450000", " ", "10", null, "Centro", "São Paulo", "SP"));

        assertEquals("Logradouro inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNumeroForInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Endereco("03450000", "Rua A", " ", null, "Centro", "São Paulo", "SP"));

        assertEquals("Número inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoBairroForInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Endereco("03450000", "Rua A", "10", null, " ", "São Paulo", "SP"));

        assertEquals("Bairro inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoCidadeForInvalida() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Endereco("03450000", "Rua A", "10", null, "Centro", " ", "SP"));

        assertEquals("Cidade inválida", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoEstadoForInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Endereco("03450000", "Rua A", "10", null, "Centro", "São Paulo", " "));

        assertEquals("Estado inválido", ex.getMessage());
    }
}
