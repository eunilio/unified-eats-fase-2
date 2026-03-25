package br.com.fiap.unifiedeats2.tipousuario.core.controller;

import br.com.fiap.unifiedeats2.tipousuario.core.usecase.removertipousuario.RemoverTipoUsuarioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoverTipoUsuarioController {

    private final RemoverTipoUsuarioUseCase removerTipoUsuarioUseCase;

    public void remover(Long id) {
        removerTipoUsuarioUseCase.run(id);
    }
}