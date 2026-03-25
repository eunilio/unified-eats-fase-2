package br.com.fiap.unifiedeats2.usuario.core.usecase.listarusuarios;

import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.core.mapper.ListarUsuariosOutputMapper;

import java.util.List;

public class ListarUsuariosUseCaseImpl implements ListarUsuariosUseCase {

    private final UsuarioGateway usuarioGateway;

    public ListarUsuariosUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public List<UsuarioOutputDTO> run() {
        return usuarioGateway.buscarTodos().stream()
                .map(ListarUsuariosOutputMapper::paraOutput)
                .toList();
    }
}