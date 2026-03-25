package br.com.fiap.unifiedeats2.cardapio.core.usecase.removeritemcardapio;

import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;

public class RemoverItemCardapioUseCaseImpl implements RemoverItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;

    public RemoverItemCardapioUseCaseImpl(ItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    @Override
    public void run(Long id) {
        if (id == null || itemCardapioGateway.buscarPorId(id).isEmpty()) {
            throw new RecursoNaoEncontradoException("Item do cardápio não encontrado.");
        }

        itemCardapioGateway.remover(id);
    }
}