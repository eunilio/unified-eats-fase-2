package br.com.fiap.unifiedeats2.cardapio.core.usecase.buscaritemcardapioporid;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarItemCardapioPorIdUseCaseImplTest {
    @Mock private ItemCardapioGateway itemCardapioGateway;
    @Mock private RestauranteGateway restauranteGateway;
    @InjectMocks private BuscarItemCardapioPorIdUseCaseImpl useCase;

    @Test
    void deveBuscarPorIdComSucesso() {
        ItemCardapio item = new ItemCardapio(1L, "Prato", "Desc", new BigDecimal("10.00"), true, "/a.jpg", 10L);
        Restaurante restaurante = new Restaurante(10L, "Restaurante A", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), "Brasileira", "09:00", 2L);
        when(itemCardapioGateway.buscarPorId(1L)).thenReturn(Optional.of(item));
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.of(restaurante));

        ItemCardapioOutputDTO output = useCase.run(1L);

        assertEquals(1L, output.id());
        assertEquals("Restaurante A", output.restaurante().nome());
    }

    @Test
    void deveLancarQuandoItemNaoExistir() {
        when(itemCardapioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> useCase.run(1L));
    }
}
