package br.com.fiap.unifiedeats2.restaurante.core.controller;

import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.cadastrarrestaurante.CadastrarRestauranteUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarRestauranteController {

    private final CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    public CadastrarRestauranteOutputDTO cadastrar(CadastrarRestauranteInputDTO input) {
        return cadastrarRestauranteUseCase.run(input);
    }
}