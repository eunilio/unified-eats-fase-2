package br.com.fiap.unifiedeats2.usuario.core.usecase.autenticarusuario;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.exception.CredenciaisInvalidasException;
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
class AutenticarUsuarioUseCaseImplTest {
    @Mock private UsuarioGateway usuarioGateway;
    @InjectMocks private AutenticarUsuarioUseCaseImpl useCase;

    @Test
    void deveAutenticarQuandoCredenciaisForemValidas() {
        Usuario usuario = new Usuario(1L, "Usuario", "user@email.com", "login", "123456", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), List.of(new TipoUsuario(1L, "CLIENTE")));
        when(usuarioGateway.buscarPorLogin("login")).thenReturn(Optional.of(usuario));
        AutenticarUsuarioOutputDTO output = useCase.executar(new AutenticarUsuarioInputDTO("login", "123456"));
        assertEquals(1L, output.id());
    }

    @Test
    void deveLancarQuandoCredenciaisForemInvalidas() {
        when(usuarioGateway.buscarPorLogin("login")).thenReturn(Optional.empty());
        assertThrows(CredenciaisInvalidasException.class, () -> useCase.executar(new AutenticarUsuarioInputDTO("login", "123456")));
    }
}
