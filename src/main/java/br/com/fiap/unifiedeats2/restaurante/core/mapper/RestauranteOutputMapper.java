package br.com.fiap.unifiedeats2.restaurante.core.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.mapper.EnderecoMapper;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;

public final class RestauranteOutputMapper {

    private RestauranteOutputMapper() {
    }

    public static RestauranteOutputDTO paraOutput(Restaurante restaurante) {
        return new RestauranteOutputDTO(
                restaurante.id(),
                restaurante.nome(),
                EnderecoMapper.paraOutputDTO(restaurante.endereco()),
                restaurante.tipoCozinha(),
                restaurante.horarioFuncionamento(),
                restaurante.donoId()
        );
    }
}