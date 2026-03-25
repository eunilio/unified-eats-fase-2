package br.com.fiap.unifiedeats2.usuario.core.usecase.atualizarusuario;

import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.AtualizarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarUsuarioUseCaseImpl implements AtualizarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    @Override
    public void run(AtualizarUsuarioInputDTO input) {
        Usuario usuario = usuarioGateway.buscarPorId(input.id())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com id: " + input.id()));

        validarEmail(input, usuario);
        validarLogin(input, usuario);

        usuario.atualizarCadastro(
                input.nome(),
                input.email(),
                input.login(),
                input.endereco()
        );

        usuarioGateway.atualizar(usuario);
    }

    private void validarEmail(AtualizarUsuarioInputDTO input, Usuario usuario) {
        if (!usuario.possuiEmail(input.email())
                && usuarioGateway.existeUsuarioComEmail(input.email())) {
            throw new IllegalArgumentException("Usuário já existe com email informado.");
        }
    }

    private void validarLogin(AtualizarUsuarioInputDTO input, Usuario usuario) {
        if (!usuario.possuiLogin(input.login())
                && usuarioGateway.existeUsuarioComLogin(input.login())) {
            throw new IllegalArgumentException("Usuário já existe com login informado.");
        }
    }
}
