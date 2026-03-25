package br.com.fiap.unifiedeats2.tipousuario.core.usecase.listartiposusuario;

import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;

import java.util.List;

public interface ListarTiposUsuarioUseCase {
    List<TipoUsuarioOutputDTO> executar();
}