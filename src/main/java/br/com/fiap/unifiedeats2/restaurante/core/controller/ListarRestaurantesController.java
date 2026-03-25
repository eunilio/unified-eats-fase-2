package br.com.fiap.unifiedeats2.restaurante.core.controller;

import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.listarrestaurantes.ListarRestaurantesUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarRestaurantesController {

    private final ListarRestaurantesUseCase listarRestaurantesUseCase;

    public List<RestauranteOutputDTO> listar() {
        return listarRestaurantesUseCase.run();
    }
}