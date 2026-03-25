package br.com.fiap.unifiedeats2.cardapio.core.usecase.atualizaritemcardapio;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.dto.AtualizarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;

public class AtualizarItemCardapioUseCaseImpl implements AtualizarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;
    private final RestauranteGateway restauranteGateway;

    public AtualizarItemCardapioUseCaseImpl(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public void run(AtualizarItemCardapioInputDTO input) {
        validarExistenciaItem(input.id());
        validarRestaurante(input.restauranteId());

        ItemCardapio itemCardapio = new ItemCardapio(
                input.id(),
                input.nome(),
                input.descricao(),
                input.preco(),
                input.disponivelApenasNoLocal(),
                input.foto(),
                input.restauranteId()
        );

        itemCardapioGateway.atualizar(itemCardapio);
    }

    private void validarExistenciaItem(Long id) {
        if (id == null || itemCardapioGateway.buscarPorId(id).isEmpty()) {
            throw new RecursoNaoEncontradoException("Item do cardápio não encontrado.");
        }
    }

    private void validarRestaurante(Long restauranteId) {
        if (restauranteId == null || restauranteGateway.buscarPorId(restauranteId).isEmpty()) {
            throw new RecursoNaoEncontradoException("Restaurante não encontrado.");
        }
    }
}