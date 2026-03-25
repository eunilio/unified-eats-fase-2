package br.com.fiap.unifiedeats2.cardapio.core.gateway;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface ItemCardapioGateway {

    ItemCardapio salvar(ItemCardapio itemCardapio);

    ItemCardapio atualizar(ItemCardapio itemCardapio);

    Optional<ItemCardapio> buscarPorId(Long id);

    List<ItemCardapio> listarTodos();

    void remover(Long id);
}