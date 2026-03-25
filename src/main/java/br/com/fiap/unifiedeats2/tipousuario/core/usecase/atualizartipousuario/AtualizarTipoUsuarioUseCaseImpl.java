package br.com.fiap.unifiedeats2.tipousuario.core.usecase.atualizartipousuario;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.AtualizarTipoUsuarioInputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarTipoUsuarioUseCaseImpl implements AtualizarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    @Override
    public void run(AtualizarTipoUsuarioInputDTO input) {
        TipoUsuario tipoUsuario = tipoUsuarioGateway.buscarPorId(input.id())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException("Tipo de usuário não encontrado com id: " + input.id()));

        if (!tipoUsuario.nome().equalsIgnoreCase(input.nome())
                && tipoUsuarioGateway.existePorNome(input.nome())) {
            throw new IllegalStateException("Já existe tipo de usuário com nome informado.");
        }

        tipoUsuario.atualizarCadastro(input.nome());

        tipoUsuarioGateway.atualizar(tipoUsuario);
    }
}