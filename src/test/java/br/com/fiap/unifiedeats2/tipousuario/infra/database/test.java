package br.com.fiap.unifiedeats2.tipousuario.infra.database;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.infra.database.entity.TipoUsuarioEntity;
import br.com.fiap.unifiedeats2.tipousuario.infra.database.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.unifiedeats2.tipousuario.infra.database.repository.TipoUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TipoUsuarioJpaGatewayTest {

    private TipoUsuarioRepository tipoUsuarioRepository;
    private TipoUsuarioEntityMapper tipoUsuarioEntityMapper;
    private TipoUsuarioJpaGateway gateway;

    @BeforeEach
    void setUp() {
        tipoUsuarioRepository = mock(TipoUsuarioRepository.class);
        tipoUsuarioEntityMapper = mock(TipoUsuarioEntityMapper.class);
        gateway = new TipoUsuarioJpaGateway(tipoUsuarioRepository, tipoUsuarioEntityMapper);
    }

    @Test
    void deveSalvar() {
        TipoUsuario domain = new TipoUsuario(1L, "CLIENTE");
        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        TipoUsuarioEntity salvo = new TipoUsuarioEntity();
        TipoUsuario retorno = new TipoUsuario(1L, "CLIENTE");

        when(tipoUsuarioEntityMapper.toEntity(domain)).thenReturn(entity);
        when(tipoUsuarioRepository.save(entity)).thenReturn(salvo);
        when(tipoUsuarioEntityMapper.toDomain(salvo)).thenReturn(retorno);

        TipoUsuario result = gateway.salvar(domain);

        assertEquals(retorno, result);
    }

    @Test
    void deveBuscarPorId() {
        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        TipoUsuario retorno = new TipoUsuario(1L, "CLIENTE");

        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(tipoUsuarioEntityMapper.toDomain(entity)).thenReturn(retorno);

        Optional<TipoUsuario> result = gateway.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(retorno, result.get());
    }

    @Test
    void deveBuscarTodos() {
        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        TipoUsuario retorno = new TipoUsuario(1L, "CLIENTE");

        when(tipoUsuarioRepository.findAll()).thenReturn(List.of(entity));
        when(tipoUsuarioEntityMapper.toDomain(entity)).thenReturn(retorno);

        List<TipoUsuario> result = gateway.buscarTodos();

        assertEquals(1, result.size());
        assertEquals(retorno, result.get(0));
    }

    @Test
    void deveVerificarExistenciaPorNome() {
        when(tipoUsuarioRepository.existsByNomeIgnoreCase("CLIENTE")).thenReturn(true);

        assertTrue(gateway.existePorNome("CLIENTE"));
    }

    @Test
    void deveAtualizar() {
        TipoUsuario domain = new TipoUsuario(1L, "CLIENTE");
        TipoUsuarioEntity entity = new TipoUsuarioEntity();

        when(tipoUsuarioEntityMapper.toEntity(domain)).thenReturn(entity);

        gateway.atualizar(domain);

        verify(tipoUsuarioRepository).save(entity);
    }

    @Test
    void deveRemover() {
        gateway.remover(1L);

        verify(tipoUsuarioRepository).deleteById(1L);
    }
}