package br.com.fiap.unifiedeats2.usuario.core.usecase.atualizarusuario;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.AtualizarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarUsuarioUseCaseImplTest {
    @Mock private UsuarioGateway usuarioGateway;
    @InjectMocks private AtualizarUsuarioUseCaseImpl useCase;

    @Test
    void deveAtualizarUsuarioQuandoExistir() {
        Usuario usuario = new Usuario(1L, "Usuario", "user@email.com", "login", "123456", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), List.of(new TipoUsuario(1L, "CLIENTE")));
        AtualizarUsuarioInputDTO input = new AtualizarUsuarioInputDTO(1L, "Usuario Atualizado", "novo@email.com", "novo.login", new Endereco("2","Rua Nova","2","Apto","Centro","São Paulo","SP"));
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(usuario));
        when(usuarioGateway.existeUsuarioComEmail("novo@email.com")).thenReturn(false);
        when(usuarioGateway.existeUsuarioComLogin("novo.login")).thenReturn(false);

        useCase.run(input);

        verify(usuarioGateway).atualizar(any(Usuario.class));
    }

    @Test
    void deveLancarQuandoUsuarioNaoExistir() {
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.run(new AtualizarUsuarioInputDTO(1L, "Nome", "email@email.com", "login", new Endereco("1","Rua","1","","Bairro","Cidade","SP"))));
    }
}
