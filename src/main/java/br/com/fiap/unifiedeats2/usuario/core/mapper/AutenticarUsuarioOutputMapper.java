package br.com.fiap.unifiedeats2.usuario.core.mapper;

import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioOutputDTO;

public final class AutenticarUsuarioOutputMapper {

    private AutenticarUsuarioOutputMapper() {
    }

    public static AutenticarUsuarioOutputDTO paraOutput(Usuario usuario) {
        return new AutenticarUsuarioOutputDTO(
                usuario.id(),
                usuario.nome(),
                usuario.email(),
                usuario.login()
        );
    }
}