package br.com.fiap.unifiedeats2.cardapio.core.controller;

import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.buscaritemcardapioporid.BuscarItemCardapioPorIdUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarItemCardapioPorIdController {

    private final BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase;

    public ItemCardapioOutputDTO buscarPorId(Long id) {
        return buscarItemCardapioPorIdUseCase.run(id);
    }
}