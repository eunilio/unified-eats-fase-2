package br.com.fiap.unifiedeats2.tipousuario.core.controller;

import br.com.fiap.unifiedeats2.tipousuario.core.dto.CadastrarTipoUsuarioInputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.usecase.cadastrartipousuario.CadastrarTipoUsuarioUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarTipoUsuarioController {

    private final CadastrarTipoUsuarioUseCase cadastrarTipoUsuarioUseCase;

    public TipoUsuarioOutputDTO cadastrar(CadastrarTipoUsuarioInputDTO input) {
        return cadastrarTipoUsuarioUseCase.run(input);
    }
}