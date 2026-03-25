package br.com.fiap.unifiedeats2.cardapio.core.usecase.buscaritemcardapioporid;

import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;

public interface BuscarItemCardapioPorIdUseCase {
    ItemCardapioOutputDTO run(Long id);
}