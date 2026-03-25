package br.com.fiap.unifiedeats2.usuario.core.usecase.removerusuario;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverUsuarioUseCaseImplTest {
    @Mock private UsuarioGateway usuarioGateway;
    @InjectMocks private RemoverUsuarioUseCaseImpl useCase;

    @Test
    void deveRemoverQuandoExistir() {
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(new Usuario(1L, "Usuario", "user@email.com", "login", "123456", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), List.of(new TipoUsuario(1L, "CLIENTE")))));
        useCase.run(1L);
        verify(usuarioGateway).remover(1L);
    }

    @Test
    void deveLancarQuandoNaoExistir() {
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.run(1L));
    }
}
