package br.com.fiap.unifiedeats2.restaurante.core.usecase.listarrestaurantes;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarRestaurantesUseCaseImplTest {
    @Mock private RestauranteGateway restauranteGateway;
    @InjectMocks private ListarRestaurantesUseCaseImpl useCase;

    @Test
    void deveListarRestaurantes() {
        when(restauranteGateway.listarTodos()).thenReturn(List.of(new Restaurante(10L, "Restaurante A", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), "Brasileira", "09:00", 2L)));
        List<RestauranteOutputDTO> outputs = useCase.run();
        assertEquals(1, outputs.size());
        assertEquals("Restaurante A", outputs.get(0).nome());
    }
}
