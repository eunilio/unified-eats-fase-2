package br.com.fiap.unifiedeats2.cardapio.core.usecase.atualizaritemcardapio;

import br.com.fiap.unifiedeats2.cardapio.core.dto.AtualizarItemCardapioInputDTO;

public interface AtualizarItemCardapioUseCase {
    void run(AtualizarItemCardapioInputDTO input);
}