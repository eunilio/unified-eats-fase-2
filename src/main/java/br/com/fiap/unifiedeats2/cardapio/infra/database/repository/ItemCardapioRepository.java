package br.com.fiap.unifiedeats2.cardapio.infra.database.repository;

import br.com.fiap.unifiedeats2.cardapio.infra.database.entity.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {
}