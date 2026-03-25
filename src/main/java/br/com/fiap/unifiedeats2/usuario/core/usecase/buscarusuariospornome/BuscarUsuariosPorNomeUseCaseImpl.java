package br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuariospornome;

import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.exception.ParametroInvalidoException;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.core.mapper.ListarUsuariosOutputMapper;

import java.security.InvalidParameterException;
import java.util.List;

public class BuscarUsuariosPorNomeUseCaseImpl implements BuscarUsuariosPorNomeUseCase {

    private final UsuarioGateway usuarioGateway;

    public BuscarUsuariosPorNomeUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public List<UsuarioOutputDTO> run(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new ParametroInvalidoException("nome", "Parâmetro 'nome' é obrigatório");

        return usuarioGateway.buscarPorNome(nome).stream()
                .map(ListarUsuariosOutputMapper::paraOutput)
                .toList();
    }
}