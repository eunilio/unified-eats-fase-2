package br.com.fiap.unifiedeats2.tipousuario.core.usecase.cadastrartipousuario;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.DadoDuplicadoException;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.CadastrarTipoUsuarioInputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarTipoUsuarioUseCaseImplTest {
    @Mock private TipoUsuarioGateway tipoUsuarioGateway;
    @InjectMocks private CadastrarTipoUsuarioUseCaseImpl useCase;

    @Test
    void deveCadastrarComSucesso() {
        when(tipoUsuarioGateway.existePorNome("CLIENTE")).thenReturn(false);
        when(tipoUsuarioGateway.salvar(any(TipoUsuario.class))).thenReturn(new TipoUsuario(1L, "CLIENTE"));
        TipoUsuarioOutputDTO output = useCase.run(new CadastrarTipoUsuarioInputDTO("CLIENTE"));
        assertEquals(1L, output.id());
    }

    @Test
    void deveLancarQuandoNomeDuplicado() {
        when(tipoUsuarioGateway.existePorNome("CLIENTE")).thenReturn(true);
        assertThrows(DadoDuplicadoException.class, () -> useCase.run(new CadastrarTipoUsuarioInputDTO("CLIENTE")));
    }
}
