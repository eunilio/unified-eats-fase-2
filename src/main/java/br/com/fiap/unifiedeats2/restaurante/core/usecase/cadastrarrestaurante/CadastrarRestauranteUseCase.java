package br.com.fiap.unifiedeats2.restaurante.core.usecase.cadastrarrestaurante;

import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteOutputDTO;

public interface CadastrarRestauranteUseCase {
    CadastrarRestauranteOutputDTO run(CadastrarRestauranteInputDTO input);
}
