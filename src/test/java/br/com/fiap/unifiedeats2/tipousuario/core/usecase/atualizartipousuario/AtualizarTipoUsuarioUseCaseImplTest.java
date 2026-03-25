package br.com.fiap.unifiedeats2.tipousuario.core.usecase.atualizartipousuario;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.AtualizarTipoUsuarioInputDTO;
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
class AtualizarTipoUsuarioUseCaseImplTest {
    @Mock private TipoUsuarioGateway tipoUsuarioGateway;
    @InjectMocks private AtualizarTipoUsuarioUseCaseImpl useCase;

    @Test
    void deveAtualizarQuandoExistir() {
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(new TipoUsuario(1L, "CLIENTE")));
        when(tipoUsuarioGateway.existePorNome("CLIENTE_ATUALIZADO")).thenReturn(false);
        useCase.run(new AtualizarTipoUsuarioInputDTO(1L, "CLIENTE_ATUALIZADO"));
        verify(tipoUsuarioGateway).atualizar(any(TipoUsuario.class));
    }

    @Test
    void deveLancarQuandoNaoExistir() {
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> useCase.run(new AtualizarTipoUsuarioInputDTO(1L, "CLIENTE_ATUALIZADO")));
    }
}
