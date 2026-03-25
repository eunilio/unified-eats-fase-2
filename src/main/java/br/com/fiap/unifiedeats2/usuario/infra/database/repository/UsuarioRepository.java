package br.com.fiap.unifiedeats2.usuario.infra.database.repository;

import aj.org.objectweb.asm.commons.Remapper;
import br.com.fiap.unifiedeats2.usuario.infra.database.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    List<UsuarioEntity> findByNomeContainingIgnoreCase(String nome);

    Optional<UsuarioEntity> findByLogin(String login);
}
