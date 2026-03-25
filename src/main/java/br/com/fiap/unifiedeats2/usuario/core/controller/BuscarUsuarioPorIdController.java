package br.com.fiap.unifiedeats2.usuario.core.controller;

import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuarioporid.BuscarUsuarioPorIdUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarUsuarioPorIdController {

    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    public UsuarioOutputDTO buscarPorId(Long id) {
        return buscarUsuarioPorIdUseCase.run(id);
    }
}