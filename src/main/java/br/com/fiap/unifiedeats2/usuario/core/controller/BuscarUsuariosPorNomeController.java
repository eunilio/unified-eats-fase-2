package br.com.fiap.unifiedeats2.usuario.core.controller;

import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuariospornome.BuscarUsuariosPorNomeUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BuscarUsuariosPorNomeController {

    private final BuscarUsuariosPorNomeUseCase buscarUsuariosPorNomeUseCase;

    public List<UsuarioOutputDTO> buscarPorNome(String nome) {
        return buscarUsuariosPorNomeUseCase.run(nome);
    }
}