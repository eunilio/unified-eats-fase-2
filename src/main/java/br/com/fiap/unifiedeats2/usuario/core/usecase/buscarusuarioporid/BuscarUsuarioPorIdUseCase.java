package br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuarioporid;

import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;

public interface BuscarUsuarioPorIdUseCase {
    UsuarioOutputDTO run(Long id);
}