package br.com.fiap.unifiedeats2.tipousuario.core.usecase.listartiposusuario;

import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import br.com.fiap.unifiedeats2.tipousuario.core.mapper.TipoUsuarioOutputMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarTiposUsuarioUseCaseImpl implements ListarTiposUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    @Override
    public List<TipoUsuarioOutputDTO> executar() {
        return tipoUsuarioGateway.buscarTodos().stream()
                .map(TipoUsuarioOutputMapper::paraOutput)
                .toList();
    }
}