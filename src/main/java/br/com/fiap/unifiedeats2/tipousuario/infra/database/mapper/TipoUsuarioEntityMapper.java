package br.com.fiap.unifiedeats2.tipousuario.infra.database.mapper;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.infra.database.entity.TipoUsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoUsuarioEntityMapper {

    public TipoUsuarioEntity toEntity(TipoUsuario tipoUsuario) {
        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        entity.setId(tipoUsuario.id());
        entity.setNome(tipoUsuario.nome());
        entity.setUltimaAtualizacao(tipoUsuario.ultimaAtualizacao());
        return entity;
    }

    public TipoUsuario toDomain(TipoUsuarioEntity entity) {
        return new TipoUsuario(
                entity.getId(),
                entity.getNome(),
                entity.getUltimaAtualizacao()
        );
    }
}