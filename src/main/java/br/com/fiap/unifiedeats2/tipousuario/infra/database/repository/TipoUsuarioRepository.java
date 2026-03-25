package br.com.fiap.unifiedeats2.tipousuario.infra.database.repository;

import br.com.fiap.unifiedeats2.tipousuario.infra.database.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}