package br.com.fiap.unifiedeats2.tipousuario.core.usecase.buscartipousuarioporid;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import br.com.fiap.unifiedeats2.tipousuario.core.mapper.TipoUsuarioOutputMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarTipoUsuarioPorIdUseCaseImpl implements BuscarTipoUsuarioPorIdUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    @Override
    public TipoUsuarioOutputDTO run(Long id) {
        TipoUsuario tipoUsuario = tipoUsuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException("Tipo de usuário não encontrado com id: " + id));

        return TipoUsuarioOutputMapper.paraOutput(tipoUsuario);
    }
}