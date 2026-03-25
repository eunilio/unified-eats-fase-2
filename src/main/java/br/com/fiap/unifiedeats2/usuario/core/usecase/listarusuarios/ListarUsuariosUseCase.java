package br.com.fiap.unifiedeats2.usuario.core.usecase.listarusuarios;

import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;

import java.util.List;

public interface ListarUsuariosUseCase {
    List<UsuarioOutputDTO> run();
}