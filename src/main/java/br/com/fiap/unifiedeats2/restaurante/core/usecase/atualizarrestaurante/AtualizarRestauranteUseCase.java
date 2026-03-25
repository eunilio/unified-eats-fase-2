package br.com.fiap.unifiedeats2.restaurante.core.usecase.atualizarrestaurante;

import br.com.fiap.unifiedeats2.restaurante.core.dto.AtualizarRestauranteInputDTO;

public interface AtualizarRestauranteUseCase {
    void run(AtualizarRestauranteInputDTO input);
}