package br.com.fiap.unifiedeats2.usuario.core.usecase.autenticarusuario;

import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.exception.CredenciaisInvalidasException;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.core.mapper.AutenticarUsuarioOutputMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AutenticarUsuarioUseCaseImpl implements AutenticarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    @Override
    public AutenticarUsuarioOutputDTO executar(AutenticarUsuarioInputDTO input) {

        Usuario usuario = usuarioGateway.buscarPorLogin(input.login())
                .orElseThrow(() -> new CredenciaisInvalidasException("Login ou senha inválidos."));

        if (!usuario.senhaIgualA(input.senha())) {
            throw new CredenciaisInvalidasException("Login ou senha inválidos.");
        }

        return AutenticarUsuarioOutputMapper.paraOutput(usuario);
    }
}