package br.com.fiap.unifiedeats2.usuario.core.mapper;

import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioOutputDTO;

public final class UsuarioOutputMapper {
    private UsuarioOutputMapper() {
    }

    public static CadastrarUsuarioOutputDTO paraOutput(Usuario usuario) {
        return new CadastrarUsuarioOutputDTO(
                usuario.id()
        );
    }
}