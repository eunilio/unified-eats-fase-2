package br.com.fiap.unifiedeats2.cardapio.core.usecase.listaritenscardapio;

import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.cardapio.core.mapper.ItemCardapioOutputMapper;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;

import java.util.List;

public class ListarItensCardapioUseCaseImpl implements ListarItensCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;
    private final RestauranteGateway restauranteGateway;

    public ListarItensCardapioUseCaseImpl(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public List<ItemCardapioOutputDTO> run() {
        return itemCardapioGateway.listarTodos()
                .stream()
                .map(itemCardapio -> {
                    var restaurante = restauranteGateway.buscarPorId(itemCardapio.restauranteId())
                            .orElseThrow(() -> new RecursoNaoEncontradoException("Restaurante não encontrado."));

                    return ItemCardapioOutputMapper.paraOutput(itemCardapio, restaurante);
                })
                .toList();
    }
}