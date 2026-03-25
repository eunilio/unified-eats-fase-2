package br.com.fiap.unifiedeats2.restaurante.core.usecase.removerrestaurante;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverRestauranteUseCaseImplTest {
    @Mock private RestauranteGateway restauranteGateway;
    @InjectMocks private RemoverRestauranteUseCaseImpl useCase;

    @Test
    void deveRemoverQuandoExistir() {
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.of(new Restaurante(10L, "Restaurante A", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), "Brasileira", "09:00", 2L)));
        useCase.run(10L);
        verify(restauranteGateway).remover(10L);
    }

    @Test
    void deveLancarQuandoNaoExistir() {
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> useCase.run(10L));
    }
}
