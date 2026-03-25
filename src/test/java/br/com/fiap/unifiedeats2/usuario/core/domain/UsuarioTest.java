package br.com.fiap.unifiedeats2.usuario.core.domain;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveCriarUsuarioValido() {
        Usuario usuario = novoUsuario();

        assertNull(usuario.id());
        assertEquals("Usuario Teste", usuario.nome());
        assertEquals("usuario@email.com", usuario.email());
        assertEquals("usuario.login", usuario.login());
        assertEquals("12345", usuario.senha());
        assertNotNull(usuario.ultimaAtualizacao());
        assertEquals(1, usuario.tipoUsuarios().size());
    }

    @Test
    void deveCriarUsuarioComIdNoConstrutorCompleto() {
        Usuario usuario = new Usuario(99L, "Usuario Teste", "usuario@email.com", "usuario.login", "12345", endereco(), tipos());

        assertEquals(99L, usuario.id());
    }

    @Test
    void deveValidarEmailLoginESenha() {
        Usuario usuario = novoUsuario();

        assertTrue(usuario.possuiEmail("USUARIO@email.com"));
        assertTrue(usuario.possuiLogin("USUARIO.LOGIN"));
        assertTrue(usuario.senhaIgualA("12345"));
        assertFalse(usuario.senhaIgualA("54321"));
    }

    @Test
    void deveRetornarCopiaDefensivaDaListaDeTipos() {
        Usuario usuario = novoUsuario();
        List<TipoUsuario> tipos = usuario.tipoUsuarios();

        assertThrows(UnsupportedOperationException.class, () -> tipos.add(new TipoUsuario(2L, "DONO")));
    }

    @Test
    void deveAtualizarCadastro() {
        Usuario usuario = novoUsuario();
        LocalDateTime antes = usuario.ultimaAtualizacao();
        Endereco novoEndereco = new Endereco("11111111", "Rua B", "20", "Casa", "Bairro", "Cidade", "rj");

        usuario.atualizarCadastro("Novo Nome", "novo@email.com", "novo.login", novoEndereco);

        assertEquals("Novo Nome", usuario.nome());
        assertEquals("novo@email.com", usuario.email());
        assertEquals("novo.login", usuario.login());
        assertEquals(novoEndereco, usuario.endereco());
        assertTrue(!usuario.ultimaAtualizacao().isBefore(antes));
    }

    @Test
    void deveAlterarSenha() {
        Usuario usuario = novoUsuario();
        LocalDateTime antes = usuario.ultimaAtualizacao();

        usuario.alterarSenha("novaSenha");

        assertEquals("novaSenha", usuario.senha());
        assertTrue(!usuario.ultimaAtualizacao().isBefore(antes));
    }

    @Test
    void deveAdicionarTipoUsuario() {
        Usuario usuario = novoUsuario();

        usuario.adicionaTipoUsuario(new TipoUsuario(2L, "DONO"));

        assertEquals(2, usuario.tipoUsuarios().size());
    }

    @Test
    void deveRemoverTipoUsuario() {
        TipoUsuario cliente = new TipoUsuario(1L, "CLIENTE");
        TipoUsuario dono = new TipoUsuario(2L, "DONO");
        Usuario usuario = new Usuario("Usuario Teste", "usuario@email.com", "usuario.login", "12345", endereco(), new ArrayList<>(List.of(cliente, dono)));

        usuario.removerTipoUsuario(dono);

        assertEquals(1, usuario.tipoUsuarios().size());
        assertEquals("CLIENTE", usuario.tipoUsuarios().get(0).nome());
    }

    @Test
    void deveLancarExcecaoQuandoAdicionarTipoNulo() {
        Usuario usuario = novoUsuario();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> usuario.adicionaTipoUsuario(null));

        assertEquals("Tipo Usuário inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoRemoverTipoNulo() {
        Usuario usuario = novoUsuario();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> usuario.removerTipoUsuario(null));

        assertEquals("Tipo Usuário inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoRemoverUltimoTipo() {
        Usuario usuario = novoUsuario();
        TipoUsuario tipo = usuario.tipoUsuarios().get(0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> usuario.removerTipoUsuario(tipo));

        assertEquals("Tipo Usuário inválido", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoTiposForemDuplicados() {
        TipoUsuario tipo = new TipoUsuario(1L, "CLIENTE");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Usuario("Usuario Teste", "usuario@email.com", "usuario.login", "12345", endereco(), List.of(tipo, tipo)));

        assertTrue(ex.getMessage().startsWith("Tipo Usuário duplicado:"));
    }

    @Test
    void deveLancarExcecaoQuandoNomeEmailLoginSenhaOuEnderecoForemInvalidos() {
        assertEquals("Nome inválido", assertThrows(IllegalArgumentException.class,
                () -> new Usuario("ab", "usuario@email.com", "usuario.login", "12345", endereco(), tipos())).getMessage());

        assertEquals("Email inválido.", assertThrows(IllegalArgumentException.class,
                () -> new Usuario("Usuario Teste", "email-invalido", "usuario.login", "12345", endereco(), tipos())).getMessage());

        assertEquals("Login inválido", assertThrows(IllegalArgumentException.class,
                () -> new Usuario("Usuario Teste", "usuario@email.com", "ab", "12345", endereco(), tipos())).getMessage());

        assertEquals("Senha inválida", assertThrows(IllegalArgumentException.class,
                () -> new Usuario("Usuario Teste", "usuario@email.com", "usuario.login", "1234", endereco(), tipos())).getMessage());

        assertEquals("Endereço inválido", assertThrows(IllegalArgumentException.class,
                () -> new Usuario("Usuario Teste", "usuario@email.com", "usuario.login", "12345", null, tipos())).getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoListaDeTiposForNulaVaziaOuContiverNulo() {
        assertEquals("Tipo Usuário inválido", assertThrows(IllegalArgumentException.class,
                () -> new Usuario("Usuario Teste", "usuario@email.com", "usuario.login", "12345", endereco(), null)).getMessage());

        assertEquals("Tipo Usuário inválido", assertThrows(IllegalArgumentException.class,
                () -> new Usuario("Usuario Teste", "usuario@email.com", "usuario.login", "12345", endereco(), List.of())).getMessage());

        assertThrows(NullPointerException.class,
                () -> new Usuario("Usuário Teste", "usuario@email.com", "usuario.login", "12345", endereco(), List.of((TipoUsuario) null))
        );
    }

    private Usuario novoUsuario() {
        return new Usuario("Usuario Teste", "usuario@email.com", "usuario.login", "12345", endereco(), tipos());
    }

    private Endereco endereco() {
        return new Endereco("03450000", "Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP");
    }

    private List<TipoUsuario> tipos() {
        return new ArrayList<>(List.of(new TipoUsuario(1L, "CLIENTE")));
    }
}
