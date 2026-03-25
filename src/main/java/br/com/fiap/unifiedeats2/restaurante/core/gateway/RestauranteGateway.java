package br.com.fiap.unifiedeats2.restaurante.core.gateway;

import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Restaurante salvar(Restaurante restaurante);

    Restaurante atualizar(Restaurante restaurante);

    Optional<Restaurante> buscarPorId(Long id);

    List<Restaurante> listarTodos();

    void remover(Long id);

    boolean existePorId(Long id);
}