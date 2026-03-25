package br.com.fiap.unifiedeats2.cardapio.core.usecase.listaritenscardapio;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarItensCardapioUseCaseImplTest {
    @Mock private ItemCardapioGateway itemCardapioGateway;
    @Mock private RestauranteGateway restauranteGateway;
    @InjectMocks private ListarItensCardapioUseCaseImpl useCase;

    @Test
    void deveListarItens() {
        ItemCardapio item = new ItemCardapio(1L, "Prato", "Desc", new BigDecimal("10.00"), true, "/a.jpg", 10L);
        Restaurante restaurante = new Restaurante(10L, "Restaurante A", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), "Brasileira", "09:00", 2L);
        when(itemCardapioGateway.listarTodos()).thenReturn(List.of(item));
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.of(restaurante));

        List<ItemCardapioOutputDTO> outputs = useCase.run();

        assertEquals(1, outputs.size());
        assertEquals("Restaurante A", outputs.get(0).restaurante().nome());
    }
}
