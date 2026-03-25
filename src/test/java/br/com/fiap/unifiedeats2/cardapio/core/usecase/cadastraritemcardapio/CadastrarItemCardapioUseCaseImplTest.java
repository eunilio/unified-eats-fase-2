package br.com.fiap.unifiedeats2.cardapio.core.usecase.cadastraritemcardapio;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarItemCardapioUseCaseImplTest {

    @Mock
    private ItemCardapioGateway itemCardapioGateway;
    @Mock
    private RestauranteGateway restauranteGateway;
    @InjectMocks
    private CadastrarItemCardapioUseCaseImpl useCase;

    private CadastrarItemCardapioInputDTO input;

    @BeforeEach
    void setUp() {
        input = new CadastrarItemCardapioInputDTO("Prato Feito", "Arroz, feijão e bife", new BigDecimal("29.90"), true, "/imagens/prato.jpg", 1L);
    }

    @Test
    void deveCadastrarQuandoRestauranteExistir() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante A", new Endereco("03450020", "Rua A", "10", "Loja 1", "Vila Carrão", "São Paulo", "SP"), "Brasileira", "09:00 às 22:00", 2L);
        ItemCardapio salvo = new ItemCardapio(100L, input.nome(), input.descricao(), input.preco(), input.disponivelApenasNoLocal(), input.foto(), input.restauranteId());

        when(restauranteGateway.buscarPorId(1L)).thenReturn(Optional.of(restaurante));
        when(itemCardapioGateway.salvar(any(ItemCardapio.class))).thenReturn(salvo);

        CadastrarItemCardapioOutputDTO output = useCase.run(input);

        assertEquals(100L, output.id());
        assertEquals("Prato Feito", output.nome());
        verify(itemCardapioGateway).salvar(any(ItemCardapio.class));
    }

    @Test
    void deveLancarQuandoRestauranteNaoExistir() {
        when(restauranteGateway.buscarPorId(1L)).thenReturn(Optional.empty());

        RecursoNaoEncontradoException ex = assertThrows(RecursoNaoEncontradoException.class, () -> useCase.run(input));

        assertEquals("Restaurante não encontrado.", ex.getMessage());
        verify(itemCardapioGateway, never()).salvar(any());
    }
}
