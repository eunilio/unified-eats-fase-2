package br.com.fiap.unifiedeats2.tipousuario.core.controller;

import br.com.fiap.unifiedeats2.tipousuario.core.dto.AtualizarTipoUsuarioInputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.usecase.atualizartipousuario.AtualizarTipoUsuarioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarTipoUsuarioController {

    private final AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;

    public void atualizar(AtualizarTipoUsuarioInputDTO input) {
        atualizarTipoUsuarioUseCase.run(input);
    }
}