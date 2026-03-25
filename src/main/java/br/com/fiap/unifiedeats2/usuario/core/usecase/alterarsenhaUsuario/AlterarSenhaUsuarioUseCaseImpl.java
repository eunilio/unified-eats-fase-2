package br.com.fiap.unifiedeats2.usuario.core.usecase.alterarsenhaUsuario;

import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.AlterarSenhaUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.exception.SenhaAtualInvalidaException;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlterarSenhaUsuarioUseCaseImpl implements AlterarSenhaUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    @Override
    public void run(AlterarSenhaUsuarioInputDTO input) {
        Usuario usuario = usuarioGateway.buscarPorId(input.id())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com id: " + input.id()));

        if (!usuario.senhaIgualA(input.senhaAtual())) {
            throw new SenhaAtualInvalidaException("Senha atual inválida.");
        }

        usuario.alterarSenha(input.novaSenha());

        usuarioGateway.atualizar(usuario);
    }
}