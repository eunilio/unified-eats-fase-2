package br.com.fiap.unifiedeats2.tipousuario.core.mapper;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;

public final class TipoUsuarioOutputMapper {

    private TipoUsuarioOutputMapper() {
    }

    public static TipoUsuarioOutputDTO paraOutput(TipoUsuario tipoUsuario) {
        return new TipoUsuarioOutputDTO(
                tipoUsuario.id(),
                tipoUsuario.nome()
        );
    }
}