package br.com.fiap.unifiedeats2.cardapio.core.usecase.listaritenscardapio;

import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;

import java.util.List;

public interface ListarItensCardapioUseCase {
    List<ItemCardapioOutputDTO> run();
}