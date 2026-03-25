package br.com.fiap.unifiedeats2.restaurante.infra.database.repository;

import br.com.fiap.unifiedeats2.restaurante.infra.database.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}