package br.com.fiap.unifiedeats2.cardapio.core.usecase.cadastraritemcardapio;

import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioOutputDTO;

public interface CadastrarItemCardapioUseCase {
    CadastrarItemCardapioOutputDTO run(CadastrarItemCardapioInputDTO input);
}