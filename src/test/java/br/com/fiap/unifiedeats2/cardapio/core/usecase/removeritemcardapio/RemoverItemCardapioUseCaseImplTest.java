package br.com.fiap.unifiedeats2.cardapio.core.usecase.removeritemcardapio;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverItemCardapioUseCaseImplTest {
    @Mock private ItemCardapioGateway itemCardapioGateway;
    @InjectMocks private RemoverItemCardapioUseCaseImpl useCase;

    @Test
    void deveRemoverQuandoExistir() {
        when(itemCardapioGateway.buscarPorId(1L)).thenReturn(Optional.of(new ItemCardapio(1L, "Prato", "Desc", new BigDecimal("10.00"), true, "/a.jpg", 10L)));
        useCase.run(1L);
        verify(itemCardapioGateway).remover(1L);
    }

    @Test
    void deveLancarQuandoNaoExistir() {
        when(itemCardapioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> useCase.run(1L));
    }
}
