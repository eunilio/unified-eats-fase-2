package br.com.fiap.unifiedeats2.usuario.core.controller;

import br.com.fiap.unifiedeats2.usuario.core.usecase.removerusuario.RemoverUsuarioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoverUsuarioController {

    private final RemoverUsuarioUseCase removerUsuarioUseCase;

    public void remover(Long id) {
        removerUsuarioUseCase.run(id);
    }
}