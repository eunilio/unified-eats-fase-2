package br.com.fiap.unifiedeats2.usuario.core.usecase.listarusuarios;

import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.core.mapper.ListarUsuariosOutputMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarUsuariosUseCaseImpl implements ListarUsuariosUseCase {

    private final UsuarioGateway usuarioGateway;

    @Override
    public List<UsuarioOutputDTO> run() {
        return usuarioGateway.buscarTodos().stream()
                .map(ListarUsuariosOutputMapper::paraOutput)
                .toList();
    }
}