package br.com.fiap.unifiedeats2.tipousuario.core.usecase.removertipousuario;

import br.com.fiap.unifiedeats2.tipousuario.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoverTipoUsuarioUseCaseImpl implements RemoverTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    @Override
    public void run(Long id) {
        tipoUsuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException("Tipo de usuário não encontrado com id: " + id));

        tipoUsuarioGateway.remover(id);
    }
}