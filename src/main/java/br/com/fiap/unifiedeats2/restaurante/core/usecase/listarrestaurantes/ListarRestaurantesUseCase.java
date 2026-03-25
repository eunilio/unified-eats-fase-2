package br.com.fiap.unifiedeats2.restaurante.core.usecase.listarrestaurantes;

import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;

import java.util.List;

public interface ListarRestaurantesUseCase {
    List<RestauranteOutputDTO> run();
}