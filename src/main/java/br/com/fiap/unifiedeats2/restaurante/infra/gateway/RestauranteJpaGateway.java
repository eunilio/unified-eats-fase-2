package br.com.fiap.unifiedeats2.restaurante.infra.gateway;

import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import br.com.fiap.unifiedeats2.restaurante.infra.database.mapper.RestauranteEntityMapper;
import br.com.fiap.unifiedeats2.restaurante.infra.database.repository.RestauranteRepository;

import java.util.List;
import java.util.Optional;

public class RestauranteJpaGateway implements RestauranteGateway {

    private final RestauranteRepository restauranteRepository;

    public RestauranteJpaGateway(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        var entity = RestauranteEntityMapper.toEntity(restaurante);
        var entitySalva = restauranteRepository.save(entity);
        return RestauranteEntityMapper.toDomain(entitySalva);
    }

    @Override
    public Restaurante atualizar(Restaurante restaurante) {
        var entity = RestauranteEntityMapper.toEntity(restaurante);
        var entityAtualizada = restauranteRepository.save(entity);
        return RestauranteEntityMapper.toDomain(entityAtualizada);
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return restauranteRepository.findById(id)
                .map(RestauranteEntityMapper::toDomain);
    }

    @Override
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll()
                .stream()
                .map(RestauranteEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void remover(Long id) {
        restauranteRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return restauranteRepository.existsById(id);
    }
}