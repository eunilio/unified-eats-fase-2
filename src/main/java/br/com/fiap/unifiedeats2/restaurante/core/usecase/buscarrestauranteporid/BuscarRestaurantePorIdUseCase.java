package br.com.fiap.unifiedeats2.restaurante.core.usecase.buscarrestauranteporid;

import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;

public interface BuscarRestaurantePorIdUseCase {
    RestauranteOutputDTO run(Long id);
}