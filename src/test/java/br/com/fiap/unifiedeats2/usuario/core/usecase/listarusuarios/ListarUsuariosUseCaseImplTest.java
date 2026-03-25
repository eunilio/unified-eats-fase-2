package br.com.fiap.unifiedeats2.usuario.core.usecase.listarusuarios;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarUsuariosUseCaseImplTest {
    @Mock private UsuarioGateway usuarioGateway;
    @InjectMocks private ListarUsuariosUseCaseImpl useCase;

    @Test
    void deveListarUsuarios() {
        when(usuarioGateway.buscarTodos()).thenReturn(List.of(new Usuario(1L, "Usuario", "user@email.com", "login", "123456", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), List.of(new TipoUsuario(1L, "CLIENTE")))));
        List<UsuarioOutputDTO> outputs = useCase.run();
        assertEquals(1, outputs.size());
    }
}
