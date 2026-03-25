package br.com.fiap.unifiedeats2.restaurante.core.controller;

import br.com.fiap.unifiedeats2.restaurante.core.usecase.removerrestaurante.RemoverRestauranteUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoverRestauranteController {

    private final RemoverRestauranteUseCase removerRestauranteUseCase;

    public void remover(Long id) {
        removerRestauranteUseCase.run(id);
    }
}