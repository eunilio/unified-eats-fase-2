package br.com.fiap.unifiedeats2.cardapio.core.controller;

import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.listaritenscardapio.ListarItensCardapioUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarItensCardapioController {

    private final ListarItensCardapioUseCase listarItensCardapioUseCase;

    public List<ItemCardapioOutputDTO> listar() {
        return listarItensCardapioUseCase.run();
    }
}