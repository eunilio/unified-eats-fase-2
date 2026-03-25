package br.com.fiap.unifiedeats2.tipousuario.core.domain;

import br.com.fiap.unifiedeats2.tipousuario.core.exception.TipoUsuarioInvalidoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioTest {

    @Test
    void deveCriarTipoUsuarioComNomeFormatado() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "  CLIENTE  ");

        assertEquals(1L, tipoUsuario.id());
        assertEquals("CLIENTE", tipoUsuario.nome());
        assertNotNull(tipoUsuario.ultimaAtualizacao());
    }

    @Test
    void deveCriarTipoUsuarioComConstrutorCompleto() {
        LocalDateTime data = LocalDateTime.of(2026, 3, 24, 10, 0);

        TipoUsuario tipoUsuario = new TipoUsuario(2L, "ADMIN", data);

        assertEquals(2L, tipoUsuario.id());
        assertEquals("ADMIN", tipoUsuario.nome());
        assertEquals(data, tipoUsuario.ultimaAtualizacao());
    }

    @Test
    void deveAtualizarCadastro() {
        TipoUsuario tipoUsuario = new TipoUsuario(1L, "CLIENTE");
        LocalDateTime antes = tipoUsuario.ultimaAtualizacao();

        tipoUsuario.atualizarCadastro("  DONO  ");

        assertEquals("DONO", tipoUsuario.nome());
        assertTrue(!tipoUsuario.ultimaAtualizacao().isBefore(antes));
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNuloOuBranco() {
        assertThrows(TipoUsuarioInvalidoException.class, () -> new TipoUsuario(1L, null));
        assertThrows(TipoUsuarioInvalidoException.class, () -> new TipoUsuario(1L, "   "));
    }
}
