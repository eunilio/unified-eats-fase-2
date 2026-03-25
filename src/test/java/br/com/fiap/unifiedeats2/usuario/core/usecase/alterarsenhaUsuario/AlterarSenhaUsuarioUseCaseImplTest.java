package br.com.fiap.unifiedeats2.usuario.core.usecase.alterarsenhaUsuario;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.AlterarSenhaUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.exception.SenhaAtualInvalidaException;
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
class AlterarSenhaUsuarioUseCaseImplTest {
    @Mock private UsuarioGateway usuarioGateway;
    @InjectMocks private AlterarSenhaUsuarioUseCaseImpl useCase;

    @Test
    void deveAlterarSenhaQuandoSenhaAtualForValida() {
        Usuario usuario = new Usuario(1L, "Usuario", "user@email.com", "login", "123456", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), List.of(new TipoUsuario(1L, "CLIENTE")));
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(usuario));
        useCase.run(new AlterarSenhaUsuarioInputDTO(1L, "123456", "654321"));
        verify(usuarioGateway).atualizar(any(Usuario.class));
    }

    @Test
    void deveLancarQuandoUsuarioNaoExistir() {
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.run(new AlterarSenhaUsuarioInputDTO(1L, "123456", "654321")));
    }

    @Test
    void deveLancarQuandoSenhaAtualForInvalida() {
        Usuario usuario = new Usuario(1L, "Usuario", "user@email.com", "login", "123456", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), List.of(new TipoUsuario(1L, "CLIENTE")));
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(usuario));
        assertThrows(SenhaAtualInvalidaException.class, () -> useCase.run(new AlterarSenhaUsuarioInputDTO(1L, "senha.errada", "654321")));
    }
}
