package br.com.fiap.unifiedeats2.tipousuario.infra.web;

import br.com.fiap.unifiedeats2.tipousuario.core.controller.*;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.AtualizarTipoUsuarioInputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.infra.web.json.AtualizarTipoUsuarioRequestJson;
import br.com.fiap.unifiedeats2.tipousuario.infra.web.json.CadastrarTipoUsuarioRequestJson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoUsuarioApiControllerTest {

    @Mock private CadastrarTipoUsuarioController cadastrarTipoUsuarioController;
    @Mock private BuscarTipoUsuarioPorIdController buscarTipoUsuarioPorIdController;
    @Mock private ListarTiposUsuarioController listarTiposUsuarioController;
    @Mock private AtualizarTipoUsuarioController atualizarTipoUsuarioController;
    @Mock private RemoverTipoUsuarioController removerTipoUsuarioController;

    @InjectMocks
    private TipoUsuarioApiController controller;

    @Test
    void deveCadastrarTipoUsuario() {
        when(cadastrarTipoUsuarioController.cadastrar(any())).thenReturn(new TipoUsuarioOutputDTO(6L, "CLIENTE"));

        ResponseEntity<Void> response = controller.cadastrar(new CadastrarTipoUsuarioRequestJson("CLIENTE"));

        assertEquals(201, response.getStatusCode().value());
        assertEquals("/v1/tipos-usuario/6", response.getHeaders().getLocation().toString());
    }

    @Test
    void deveListarTiposUsuario() {
        List<TipoUsuarioOutputDTO> output = List.of(new TipoUsuarioOutputDTO(1L, "CLIENTE"));
        when(listarTiposUsuarioController.listar()).thenReturn(output);

        ResponseEntity<List<TipoUsuarioOutputDTO>> response = controller.listar();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }

    @Test
    void deveBuscarTipoUsuarioPorId() {
        TipoUsuarioOutputDTO output = new TipoUsuarioOutputDTO(1L, "CLIENTE");
        when(buscarTipoUsuarioPorIdController.buscarPorId(1L)).thenReturn(output);

        ResponseEntity<TipoUsuarioOutputDTO> response = controller.buscarPorId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }

    @Test
    void deveAtualizarTipoUsuario() {
        ResponseEntity<Void> response = controller.atualizar(2L, new AtualizarTipoUsuarioRequestJson("DONO"));

        ArgumentCaptor<AtualizarTipoUsuarioInputDTO> captor = ArgumentCaptor.forClass(AtualizarTipoUsuarioInputDTO.class);
        verify(atualizarTipoUsuarioController).atualizar(captor.capture());

        assertEquals(2L, captor.getValue().id());
        assertEquals("DONO", captor.getValue().nome());
        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void deveRemoverTipoUsuario() {
        ResponseEntity<Void> response = controller.remover(9L);

        verify(removerTipoUsuarioController).remover(9L);
        assertEquals(204, response.getStatusCode().value());
    }
}
