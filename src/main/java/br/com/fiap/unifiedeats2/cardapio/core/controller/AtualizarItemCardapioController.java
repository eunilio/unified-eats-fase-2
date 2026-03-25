package br.com.fiap.unifiedeats2.cardapio.core.controller;

import br.com.fiap.unifiedeats2.cardapio.core.dto.AtualizarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.atualizaritemcardapio.AtualizarItemCardapioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarItemCardapioController {

    private final AtualizarItemCardapioUseCase atualizarItemCardapioUseCase;

    public void atualizar(AtualizarItemCardapioInputDTO input) {
        atualizarItemCardapioUseCase.run(input);
    }
}