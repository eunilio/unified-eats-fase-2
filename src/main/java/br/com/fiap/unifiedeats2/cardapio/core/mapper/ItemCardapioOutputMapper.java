package br.com.fiap.unifiedeats2.cardapio.core.mapper;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.mapper.RestauranteOutputMapper;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;

public final class ItemCardapioOutputMapper {

    private ItemCardapioOutputMapper() {
    }

    public static CadastrarItemCardapioOutputDTO paraCadastrarOutput(ItemCardapio itemCardapio) {
        return new CadastrarItemCardapioOutputDTO(
                itemCardapio.id(),
                itemCardapio.nome(),
                itemCardapio.descricao(),
                itemCardapio.preco(),
                itemCardapio.disponivelApenasNoLocal(),
                itemCardapio.foto(),
                itemCardapio.restauranteId()
        );
    }

    public static ItemCardapioOutputDTO paraOutput(ItemCardapio itemCardapio, Restaurante restaurante) {
        RestauranteOutputDTO restauranteOutputDTO = RestauranteOutputMapper.paraOutput(restaurante);

        return new ItemCardapioOutputDTO(
                itemCardapio.id(),
                itemCardapio.nome(),
                itemCardapio.descricao(),
                itemCardapio.preco(),
                itemCardapio.disponivelApenasNoLocal(),
                itemCardapio.foto(),
                restauranteOutputDTO
        );
    }
}