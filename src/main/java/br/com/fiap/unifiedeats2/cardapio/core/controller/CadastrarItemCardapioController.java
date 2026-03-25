package br.com.fiap.unifiedeats2.cardapio.core.controller;

import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.cadastraritemcardapio.CadastrarItemCardapioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarItemCardapioController {

    private final CadastrarItemCardapioUseCase cadastrarItemCardapioUseCase;

    public CadastrarItemCardapioOutputDTO cadastrar(CadastrarItemCardapioInputDTO input) {
        return cadastrarItemCardapioUseCase.run(input);
    }
}