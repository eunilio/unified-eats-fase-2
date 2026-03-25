package br.com.fiap.unifiedeats2.usuario.core.controller;

import br.com.fiap.unifiedeats2.usuario.core.dto.AlterarSenhaUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.usecase.alterarsenhaUsuario.AlterarSenhaUsuarioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlterarSenhaUsuarioController {

    private final AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase;

    public void alterarSenha(AlterarSenhaUsuarioInputDTO input) {
        alterarSenhaUsuarioUseCase.run(input);
    }
}