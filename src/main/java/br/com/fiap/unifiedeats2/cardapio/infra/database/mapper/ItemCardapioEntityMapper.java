package br.com.fiap.unifiedeats2.cardapio.infra.database.mapper;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.infra.database.entity.ItemCardapioEntity;

public final class ItemCardapioEntityMapper {

    private ItemCardapioEntityMapper() {
    }

    public static ItemCardapioEntity toEntity(ItemCardapio itemCardapio) {
        return new ItemCardapioEntity(
                itemCardapio.id(),
                itemCardapio.nome(),
                itemCardapio.descricao(),
                itemCardapio.preco(),
                itemCardapio.disponivelApenasNoLocal(),
                itemCardapio.foto(),
                itemCardapio.restauranteId()
        );
    }

    public static ItemCardapio toDomain(ItemCardapioEntity entity) {
        return new ItemCardapio(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponivelApenasNoLocal(),
                entity.getFoto(),
                entity.getRestauranteId()
        );
    }
}