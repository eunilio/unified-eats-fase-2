package br.com.fiap.unifiedeats2.tipousuario.core.controller;

import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.usecase.listartiposusuario.ListarTiposUsuarioUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarTiposUsuarioController {

    private final ListarTiposUsuarioUseCase listarTiposUsuarioUseCase;

    public List<TipoUsuarioOutputDTO> listar() {
        return listarTiposUsuarioUseCase.executar();
    }
}