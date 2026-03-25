package br.com.fiap.unifiedeats2.usuario.core.usecase.cadastrarusuario;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.DadoDuplicadoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarUsuarioUseCaseImplTest {
    @Mock private UsuarioGateway usuarioGateway;
    @Mock private TipoUsuarioGateway tipoUsuarioGateway;
    @InjectMocks private CadastrarUsuarioUseCaseImpl useCase;

    private CadastrarUsuarioInputDTO input;

    @BeforeEach
    void setUp() {
        input = new CadastrarUsuarioInputDTO("Usuario Cliente", "cliente@email.com", "cliente.login", "123456", "03450000", "Rua Cliente", "100", "Casa", "Vila Carrão", "São Paulo", "SP", List.of(1L));
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        TipoUsuario tipoCliente = new TipoUsuario(1L, "CLIENTE");
        Usuario usuarioSalvo = new Usuario(10L, "Usuario Cliente", "cliente@email.com", "cliente.login", "123456", new Endereco("03450000", "Rua Cliente", "100", "Casa", "Vila Carrão", "São Paulo", "SP"), List.of(tipoCliente));
        when(usuarioGateway.existeUsuarioComEmail(input.email())).thenReturn(false);
        when(usuarioGateway.existeUsuarioComLogin(input.login())).thenReturn(false);
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(tipoCliente));
        when(usuarioGateway.salvar(any(Usuario.class))).thenReturn(usuarioSalvo);

        CadastrarUsuarioOutputDTO output = useCase.run(input);

        assertEquals(10L, output.id());
    }

    @Test
    void deveLancarQuandoEmailJaExistir() {
        when(usuarioGateway.existeUsuarioComEmail(input.email())).thenReturn(true);
        assertThrows(DadoDuplicadoException.class, () -> useCase.run(input));
    }
}
