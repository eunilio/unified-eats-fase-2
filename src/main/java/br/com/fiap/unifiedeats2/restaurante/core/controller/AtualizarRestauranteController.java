package br.com.fiap.unifiedeats2.restaurante.core.controller;

import br.com.fiap.unifiedeats2.restaurante.core.dto.AtualizarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.atualizarrestaurante.AtualizarRestauranteUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarRestauranteController {

    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    public void atualizar(AtualizarRestauranteInputDTO input) {
        atualizarRestauranteUseCase.run(input);
    }
}