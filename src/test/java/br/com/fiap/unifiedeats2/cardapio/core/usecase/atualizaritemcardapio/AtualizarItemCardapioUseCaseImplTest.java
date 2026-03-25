package br.com.fiap.unifiedeats2.cardapio.core.usecase.atualizaritemcardapio;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.dto.AtualizarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarItemCardapioUseCaseImplTest {
    @Mock private ItemCardapioGateway itemCardapioGateway;
    @Mock private RestauranteGateway restauranteGateway;
    @InjectMocks private AtualizarItemCardapioUseCaseImpl useCase;

    private AtualizarItemCardapioInputDTO input;

    @BeforeEach
    void setUp() {
        input = new AtualizarItemCardapioInputDTO(1L, "Prato Atualizado", "Descrição", new BigDecimal("35.00"), false, "/foto.jpg", 10L);
    }

    @Test
    void deveAtualizarQuandoItemERestauranteExistirem() {
        when(itemCardapioGateway.buscarPorId(1L)).thenReturn(Optional.of(new ItemCardapio(1L, "Prato", "Desc", new BigDecimal("10.00"), true, "/a.jpg", 10L)));
        when(restauranteGateway.buscarPorId(10L)).thenReturn(Optional.of(new Restaurante(10L, "Restaurante A", new Endereco("1","Rua","1","","Bairro","Cidade","SP"), "Brasileira", "09:00", 2L)));

        useCase.run(input);

        verify(itemCardapioGateway).atualizar(any(ItemCardapio.class));
    }

    @Test
    void deveLancarQuandoItemNaoExistir() {
        when(itemCardapioGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        RecursoNaoEncontradoException ex = assertThrows(RecursoNaoEncontradoException.class, () -> useCase.run(input));

        assertEquals("Item do cardápio não encontrado.", ex.getMessage());
        verify(itemCardapioGateway, never()).atualizar(any());
    }
}
