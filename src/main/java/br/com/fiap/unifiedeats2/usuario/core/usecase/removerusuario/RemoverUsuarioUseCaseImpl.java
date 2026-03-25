package br.com.fiap.unifiedeats2.usuario.core.usecase.removerusuario;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoverUsuarioUseCaseImpl implements RemoverUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    @Override
    public void run(Long id) {
        usuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com id: " + id));

        usuarioGateway.remover(id);
    }
}