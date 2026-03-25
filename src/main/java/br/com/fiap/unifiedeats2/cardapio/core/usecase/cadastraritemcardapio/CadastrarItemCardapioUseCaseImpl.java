package br.com.fiap.unifiedeats2.cardapio.core.usecase.cadastraritemcardapio;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.cardapio.core.mapper.ItemCardapioOutputMapper;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarItemCardapioUseCaseImpl implements CadastrarItemCardapioUseCase {

    private final ItemCardapioGateway itemCardapioGateway;
    private final RestauranteGateway restauranteGateway;

    @Override
    public CadastrarItemCardapioOutputDTO run(CadastrarItemCardapioInputDTO input) {
        validarRestaurante(input.restauranteId());

        ItemCardapio itemCardapio = new ItemCardapio(
                null,
                input.nome(),
                input.descricao(),
                input.preco(),
                input.disponivelApenasNoLocal(),
                input.foto(),
                input.restauranteId()
        );

        ItemCardapio itemCardapioSalvo = itemCardapioGateway.salvar(itemCardapio);

        return ItemCardapioOutputMapper.paraCadastrarOutput(itemCardapioSalvo);
    }

    private void validarRestaurante(Long restauranteId) {
        if (restauranteId == null || restauranteGateway.buscarPorId(restauranteId).isEmpty()) {
            throw new RecursoNaoEncontradoException("Restaurante não encontrado.");
        }
    }
}