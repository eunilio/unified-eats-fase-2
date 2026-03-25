package br.com.fiap.unifiedeats2.restaurante.core.usecase.removerrestaurante;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;

public class RemoverRestauranteUseCaseImpl implements RemoverRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public RemoverRestauranteUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public void run(Long id) {
        if (id == null || restauranteGateway.buscarPorId(id).isEmpty()) {
            throw new RecursoNaoEncontradoException("Restaurante não encontrado.");
        }

        restauranteGateway.remover(id);
    }
}