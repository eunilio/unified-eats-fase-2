package br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuarioporid;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.UsuarioNaoEncontradoException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarUsuarioPorIdUseCaseImplTest {
    @Mock private UsuarioGateway usuarioGateway;
    @InjectMocks private BuscarUsuarioPorIdUseCaseImpl useCase;

    @Test
    void deveBuscarUsuarioPorId() {
        Usuario usuario = new Usuario(1L, "Usuario", "user@email.com", "login", "123456", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), List.of(new TipoUsuario(1L, "CLIENTE")));
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(usuario));
        UsuarioOutputDTO output = useCase.run(1L);
        assertEquals("Usuario", output.nome());
    }

    @Test
    void deveLancarQuandoNaoEncontrar() {
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.run(1L));
    }
}
