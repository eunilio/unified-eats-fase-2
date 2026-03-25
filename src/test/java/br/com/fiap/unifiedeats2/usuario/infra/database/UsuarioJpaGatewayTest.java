package br.com.fiap.unifiedeats2.usuario.infra.database;

import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.infra.database.entity.UsuarioEntity;
import br.com.fiap.unifiedeats2.usuario.infra.database.mapper.UsuarioEntityMapper;
import br.com.fiap.unifiedeats2.usuario.infra.database.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioJpaGatewayTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioEntityMapper usuarioEntityMapper;
    private UsuarioJpaGateway gateway;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioEntityMapper = mock(UsuarioEntityMapper.class);
        gateway = new UsuarioJpaGateway(usuarioRepository, usuarioEntityMapper);
    }

    @Test
    void deveSalvar() {
        Usuario domain = mock(Usuario.class);
        UsuarioEntity entity = new UsuarioEntity();
        UsuarioEntity salvo = new UsuarioEntity();
        Usuario retorno = mock(Usuario.class);

        when(usuarioEntityMapper.toEntity(domain)).thenReturn(entity);
        when(usuarioRepository.save(entity)).thenReturn(salvo);
        when(usuarioEntityMapper.toDomain(salvo)).thenReturn(retorno);

        Usuario result = gateway.salvar(domain);

        assertEquals(retorno, result);
    }

    @Test
    void deveVerificarExistenciaPorEmail() {
        when(usuarioRepository.existsByEmail("usuario@email.com")).thenReturn(true);

        assertTrue(gateway.existeUsuarioComEmail("usuario@email.com"));
    }

    @Test
    void deveVerificarExistenciaPorLogin() {
        when(usuarioRepository.existsByLogin("usuario.login")).thenReturn(true);

        assertTrue(gateway.existeUsuarioComLogin("usuario.login"));
    }

    @Test
    void deveBuscarPorId() {
        UsuarioEntity entity = new UsuarioEntity();
        Usuario retorno = mock(Usuario.class);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(usuarioEntityMapper.toDomain(entity)).thenReturn(retorno);

        Optional<Usuario> result = gateway.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(retorno, result.get());
    }

    @Test
    void deveBuscarPorLogin() {
        UsuarioEntity entity = new UsuarioEntity();
        Usuario retorno = mock(Usuario.class);

        when(usuarioRepository.findByLogin("usuario.login")).thenReturn(Optional.of(entity));
        when(usuarioEntityMapper.toDomain(entity)).thenReturn(retorno);

        Optional<Usuario> result = gateway.buscarPorLogin("usuario.login");

        assertTrue(result.isPresent());
        assertEquals(retorno, result.get());
    }

    @Test
    void deveBuscarTodos() {
        UsuarioEntity entity = new UsuarioEntity();
        Usuario retorno = mock(Usuario.class);

        when(usuarioRepository.findAll()).thenReturn(List.of(entity));
        when(usuarioEntityMapper.toDomain(entity)).thenReturn(retorno);

        List<Usuario> result = gateway.buscarTodos();

        assertEquals(1, result.size());
        assertEquals(retorno, result.get(0));
    }

    @Test
    void deveBuscarPorNome() {
        UsuarioEntity entity = new UsuarioEntity();
        Usuario retorno = mock(Usuario.class);

        when(usuarioRepository.findByNomeContainingIgnoreCase("Felipe")).thenReturn(List.of(entity));
        when(usuarioEntityMapper.toDomain(entity)).thenReturn(retorno);

        List<Usuario> result = gateway.buscarPorNome("Felipe");

        assertEquals(1, result.size());
        assertEquals(retorno, result.get(0));
    }

    @Test
    void deveAtualizar() {
        Usuario domain = mock(Usuario.class);
        UsuarioEntity entity = new UsuarioEntity();

        when(usuarioEntityMapper.toEntity(domain)).thenReturn(entity);

        gateway.atualizar(domain);

        verify(usuarioRepository).save(entity);
    }

    @Test
    void deveRemover() {
        gateway.remover(1L);

        verify(usuarioRepository).deleteById(1L);
    }
}