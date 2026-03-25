package br.com.fiap.unifiedeats2.cardapio.core.controller;

import br.com.fiap.unifiedeats2.cardapio.core.usecase.removeritemcardapio.RemoverItemCardapioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoverItemCardapioController {

    private final RemoverItemCardapioUseCase removerItemCardapioUseCase;

    public void remover(Long id) {
        removerItemCardapioUseCase.run(id);
    }
}