package br.com.fiap.unifiedeats2.restaurante.core.usecase.buscarrestauranteporid;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.mapper.EnderecoMapper;
import br.com.fiap.unifiedeats2.restaurante.core.dto.BuscarRestaurantePorIdOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;

public class BuscarRestaurantePorIdUseCaseImpl implements BuscarRestaurantePorIdUseCase {

    private final RestauranteGateway restauranteGateway;

    public BuscarRestaurantePorIdUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public RestauranteOutputDTO run(Long id) {
        var restaurante = restauranteGateway.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Restaurante não encontrado."));

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