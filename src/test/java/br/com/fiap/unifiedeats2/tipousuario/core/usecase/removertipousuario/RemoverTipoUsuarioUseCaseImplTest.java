package br.com.fiap.unifiedeats2.tipousuario.core.usecase.removertipousuario;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverTipoUsuarioUseCaseImplTest {
    @Mock private TipoUsuarioGateway tipoUsuarioGateway;
    @InjectMocks private RemoverTipoUsuarioUseCaseImpl useCase;

    @Test
    void deveRemoverQuandoExistir() {
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(new TipoUsuario(1L, "CLIENTE")));
        useCase.run(1L);
        verify(tipoUsuarioGateway).remover(1L);
    }

    @Test
    void deveLancarQuandoNaoExistir() {
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> useCase.run(1L));
    }
}
