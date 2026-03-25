package br.com.fiap.unifiedeats2.usuario.core.controller;

import br.com.fiap.unifiedeats2.usuario.core.dto.AtualizarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.usecase.atualizarusuario.AtualizarUsuarioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarUsuarioController {

    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    public void atualizar(AtualizarUsuarioInputDTO input) {
        atualizarUsuarioUseCase.run(input);
    }
}