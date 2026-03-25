package br.com.fiap.unifiedeats2.usuario.core.controller;

import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.usecase.cadastrarusuario.CadastrarUsuarioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarUsuarioController {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    public Long cadastrar(CadastrarUsuarioInputDTO inputDto) {
        var output  = cadastrarUsuarioUseCase.run(inputDto);
        return output.id();
    }
}
