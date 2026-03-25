package br.com.fiap.unifiedeats2.restaurante.core.controller;

import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.buscarrestauranteporid.BuscarRestaurantePorIdUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarRestaurantePorIdController {

    private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    public RestauranteOutputDTO buscarPorId(Long id) {
        return buscarRestaurantePorIdUseCase.run(id);
    }
}