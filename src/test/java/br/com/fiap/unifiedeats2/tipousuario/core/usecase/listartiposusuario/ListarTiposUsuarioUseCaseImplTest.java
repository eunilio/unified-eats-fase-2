package br.com.fiap.unifiedeats2.tipousuario.core.usecase.listartiposusuario;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarTiposUsuarioUseCaseImplTest {
    @Mock private TipoUsuarioGateway tipoUsuarioGateway;
    @InjectMocks private ListarTiposUsuarioUseCaseImpl useCase;

    @Test
    void deveListarTipos() {
        when(tipoUsuarioGateway.buscarTodos()).thenReturn(List.of(new TipoUsuario(1L, "CLIENTE"), new TipoUsuario(2L, "DONO_RESTAURANTE")));
        List<TipoUsuarioOutputDTO> outputs = useCase.executar();
        assertEquals(2, outputs.size());
    }
}
