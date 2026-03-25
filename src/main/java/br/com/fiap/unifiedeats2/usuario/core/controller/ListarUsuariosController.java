package br.com.fiap.unifiedeats2.usuario.core.controller;

import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.usecase.listarusuarios.ListarUsuariosUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarUsuariosController {

    private final ListarUsuariosUseCase listarUsuariosUseCase;

    public List<UsuarioOutputDTO> listar() {
        return listarUsuariosUseCase.run();
    }
}