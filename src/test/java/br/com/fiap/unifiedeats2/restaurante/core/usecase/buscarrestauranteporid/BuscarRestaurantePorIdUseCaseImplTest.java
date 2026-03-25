package br.com.fiap.unifiedeats2.restaurante.core.usecase.buscarrestauranteporid;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarRestaurantePorIdUseCaseImplTest {
    @Mock private RestauranteGateway restauranteGateway;
    @InjectMocks private BuscarRestaurantePorIdUseCaseImpl useCase;

    @Test
    void deveBuscarPorId() {
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.of(new Restaurante(10L, "Restaurante A", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), "Brasileira", "09:00", 2L)));
        RestauranteOutputDTO output = useCase.run(10L);
        assertEquals("Restaurante A", output.nome());
    }

    @Test
    void deveLancarQuandoNaoEncontrar() {
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> useCase.run(10L));
    }
}
