package br.com.fiap.unifiedeats2.usuario.core.usecase.cadastrarusuario;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.DadoDuplicadoException;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.core.mapper.UsuarioOutputMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final TipoUsuarioGateway tipoUsuarioGateway;

    @Override
    public CadastrarUsuarioOutputDTO run(CadastrarUsuarioInputDTO input) {
        validarEmailUnico(input);
        validarLoginUnico(input);
        List<TipoUsuario> tiposUsuario = obterTiposUsuario(input);
        Endereco endereco = criarEndereco(input);
        Usuario usuario = criarUsuario(input, endereco, tiposUsuario);
        Usuario usuarioSalvo = usuarioGateway.salvar(usuario);
        return criarSaida(usuarioSalvo);
    }

    private CadastrarUsuarioOutputDTO criarSaida(Usuario usuarioSalvo) {
        return UsuarioOutputMapper.paraOutput(usuarioSalvo);
    }

    private Usuario criarUsuario(CadastrarUsuarioInputDTO input, Endereco endereco, List<TipoUsuario> tiposUsuario) {
        return new Usuario(input.nome(), input.email(), input.login(), input.senha(), endereco, tiposUsuario);
    }

    private void validarEmailUnico(CadastrarUsuarioInputDTO input) {
        if (usuarioGateway.existeUsuarioComEmail(input.email())) {
            log.warn("Usuário já existe com email informado. {}", input.email());
            throw new DadoDuplicadoException("Usuário já existe com email informado.");
        }
    }

    private void validarLoginUnico(CadastrarUsuarioInputDTO input) {
        if (usuarioGateway.existeUsuarioComLogin(input.login())) {
            log.warn("Usuário já existe com login informado. {}", input.login());
            throw new DadoDuplicadoException("Usuário já existe com login informado.");
        }
    }

    private List<TipoUsuario> obterTiposUsuario(CadastrarUsuarioInputDTO input) {
        if (input.tiposUsuario() == null || input.tiposUsuario().isEmpty()) {
            throw new IllegalArgumentException("É obrigatório informar ao menos um tipo de usuário.");
        }

        List<Long> idsTipos = input.tiposUsuario().stream()
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        if (idsTipos.isEmpty()) {
            throw new IllegalArgumentException("É obrigatório informar ao menos um tipo de usuário válido.");
        }

        List<TipoUsuario> tiposEncontrados = idsTipos.stream()
                .map(id -> tipoUsuarioGateway.buscarPorId(id)
                        .orElseThrow(() -> {
                            log.warn("Tipo de usuário não encontrado para id {}", id);
                            return new IllegalArgumentException("Tipo de usuário inválido: " + id);
                        })
                )
                .toList();

        return tiposEncontrados;
    }

    private Endereco criarEndereco(CadastrarUsuarioInputDTO input) {
        return new Endereco(input.cep(), input.logradouro(), input.numero(), input.complemento(), input.bairro(), input.cidade(), input.estado());
    }
}
