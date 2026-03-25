package br.com.fiap.unifiedeats2.restaurante.core.usecase.listarrestaurantes;

import br.com.fiap.unifiedeats2.compartilhado.core.mapper.EnderecoMapper;
import br.com.fiap.unifiedeats2.restaurante.core.dto.ListarRestaurantesOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarRestaurantesUseCaseImpl implements ListarRestaurantesUseCase {

    private final RestauranteGateway restauranteGateway;

    @Override
    public List<RestauranteOutputDTO> run() {
        return restauranteGateway.listarTodos()
                .stream()
                .map(restaurante -> new RestauranteOutputDTO(
                        restaurante.id(),
                        restaurante.nome(),
                        EnderecoMapper.paraOutputDTO(restaurante.endereco()),
                        restaurante.tipoCozinha(),
                        restaurante.horarioFuncionamento(),
                        restaurante.donoId()
                ))
                .toList();
    }
}