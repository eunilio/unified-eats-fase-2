package br.com.fiap.unifiedeats2.cardapio.core.usecase.buscaritemcardapioporid;

import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.cardapio.core.mapper.ItemCardapioOutputMapper;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarItemCardapioPorIdUseCaseImpl implements BuscarItemCardapioPorIdUseCase {

    private final ItemCardapioGateway itemCardapioGateway;
    private final RestauranteGateway restauranteGateway;

    @Override
    public ItemCardapioOutputDTO run(Long id) {
        var itemCardapio = itemCardapioGateway.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item do cardápio não encontrado."));

        var restaurante = restauranteGateway.buscarPorId(itemCardapio.restauranteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Restaurante não encontrado."));

        return ItemCardapioOutputMapper.paraOutput(itemCardapio, restaurante);
    }
}