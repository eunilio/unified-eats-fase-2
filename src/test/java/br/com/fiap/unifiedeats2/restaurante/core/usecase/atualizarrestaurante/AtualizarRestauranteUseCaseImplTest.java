package br.com.fiap.unifiedeats2.restaurante.core.usecase.atualizarrestaurante;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.dto.AtualizarRestauranteInputDTO;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarRestauranteUseCaseImplTest {
    @Mock private RestauranteGateway restauranteGateway;
    @Mock private UsuarioGateway usuarioGateway;
    @InjectMocks private AtualizarRestauranteUseCaseImpl useCase;

    private AtualizarRestauranteInputDTO input;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        endereco = new Endereco("03450020", "Rua Restaurante A", "10", "Loja 1", "Vila Carrão", "São Paulo", "SP");
        input = new AtualizarRestauranteInputDTO(10L, "Restaurante A", endereco, "Brasileira", "09:00 às 22:00", 1L);
    }

    @Test
    void deveAtualizarQuandoRestauranteEDonoExistirem() {
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.of(new Restaurante(10L, "Restaurante A", endereco, "Brasileira", "09:00 às 22:00", 1L)));
        when(usuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(new Usuario(1L, "Usuario Dono", "dono@email.com", "login.dono", "123456", endereco, List.of(new TipoUsuario(2L, "DONO_RESTAURANTE")))));

        useCase.run(input);

        verify(restauranteGateway).atualizar(any(Restaurante.class));
    }

    @Test
    void deveLancarQuandoRestauranteNaoExistir() {
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> useCase.run(input));
    }
}
