package br.com.fiap.unifiedeats2.usuario.core.controller;

import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.usecase.autenticarusuario.AutenticarUsuarioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AutenticarUsuarioController {

    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    public AutenticarUsuarioOutputDTO autenticar(AutenticarUsuarioInputDTO input) {
        return autenticarUsuarioUseCase.executar(input);
    }
}