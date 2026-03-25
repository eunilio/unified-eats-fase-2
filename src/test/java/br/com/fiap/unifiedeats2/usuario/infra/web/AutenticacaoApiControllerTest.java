package br.com.fiap.unifiedeats2.usuario.infra.web;

import br.com.fiap.unifiedeats2.usuario.core.controller.AutenticarUsuarioController;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.AutenticarUsuarioRequestJson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoApiControllerTest {

    @Mock
    private AutenticarUsuarioController autenticarUsuarioController;

    @InjectMocks
    private AutenticacaoApiController controller;

    @Test
    void deveAutenticarUsuario() {
        AutenticarUsuarioOutputDTO output = new AutenticarUsuarioOutputDTO(1L, "Felipe", "felipe@email.com", "felipe.login");
        when(autenticarUsuarioController.autenticar(any(AutenticarUsuarioInputDTO.class))).thenReturn(output);

        ResponseEntity<AutenticarUsuarioOutputDTO> response = controller.login(new AutenticarUsuarioRequestJson("felipe.login", "12345"));

        ArgumentCaptor<AutenticarUsuarioInputDTO> captor = ArgumentCaptor.forClass(AutenticarUsuarioInputDTO.class);
        verify(autenticarUsuarioController).autenticar(captor.capture());

        assertEquals("felipe.login", captor.getValue().login());
        assertEquals("12345", captor.getValue().senha());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }
}
