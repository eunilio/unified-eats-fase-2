package br.com.fiap.unifiedeats2.tipousuario.core.usecase.buscartipousuarioporid;

import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;

public interface BuscarTipoUsuarioPorIdUseCase {
    TipoUsuarioOutputDTO run(Long id);
}