package br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuariospornome;

import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;

import java.util.List;

public interface BuscarUsuariosPorNomeUseCase {
    List<UsuarioOutputDTO> run(String nome);
}