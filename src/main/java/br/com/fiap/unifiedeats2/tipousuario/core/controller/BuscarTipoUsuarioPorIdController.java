package br.com.fiap.unifiedeats2.tipousuario.core.controller;

import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.usecase.buscartipousuarioporid.BuscarTipoUsuarioPorIdUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarTipoUsuarioPorIdController {

    private final BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase;

    public TipoUsuarioOutputDTO buscarPorId(Long id) {
        return buscarTipoUsuarioPorIdUseCase.run(id);
    }
}