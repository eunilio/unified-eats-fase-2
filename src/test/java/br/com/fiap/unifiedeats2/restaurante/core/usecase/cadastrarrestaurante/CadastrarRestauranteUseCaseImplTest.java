package br.com.fiap.unifiedeats2.restaurante.core.usecase.cadastrarrestaurante;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
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
class CadastrarRestauranteUseCaseImplTest {
    @Mock private RestauranteGateway restauranteGateway;
    @Mock private UsuarioGateway usuarioGateway;
    @InjectMocks private CadastrarRestauranteUseCaseImpl useCase;

    private CadastrarRestauranteInputDTO input;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        endereco = new Endereco("03450020", "Rua Restaurante A", "10", "Loja 1", "Vila Carrão", "São Paulo", "SP");
        input = new CadastrarRestauranteInputDTO("Restaurante A", endereco, "Brasileira", "09:00 às 22:00", 1L);
    }

    @Test
    void deveCadastrarRestauranteComDonoExistente() {
        Usuario dono = new Usuario(1L, "Usuario Dono", "dono@email.com", "login.dono", "123456", endereco, List.of(new TipoUsuario(2L, "DONO_RESTAURANTE")));
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(dono));
        when(restauranteGateway.salvar(any(Restaurante.class))).thenReturn(new Restaurante(10L, input.nome(), input.endereco(), input.tipoCozinha(), input.horarioFuncionamento(), input.donoId()));

        CadastrarRestauranteOutputDTO output = useCase.run(input);

        assertEquals(10L, output.id());
    }

    @Test
    void deveLancarQuandoDonoNaoExistir() {
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> useCase.run(input));
        verify(restauranteGateway, never()).salvar(any());
    }
}
