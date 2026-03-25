package br.com.fiap.unifiedeats2.tipousuario.core.usecase.buscartipousuarioporid;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarTipoUsuarioPorIdUseCaseImplTest {
    @Mock private TipoUsuarioGateway tipoUsuarioGateway;
    @InjectMocks private BuscarTipoUsuarioPorIdUseCaseImpl useCase;

    @Test
    void deveBuscarPorId() {
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(new TipoUsuario(1L, "CLIENTE")));
        TipoUsuarioOutputDTO output = useCase.run(1L);
        assertEquals("CLIENTE", output.nome());
    }

    @Test
    void deveLancarQuandoNaoEncontrar() {
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> useCase.run(1L));
    }
}
