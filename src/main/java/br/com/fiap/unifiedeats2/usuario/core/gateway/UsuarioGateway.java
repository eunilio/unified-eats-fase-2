package br.com.fiap.unifiedeats2.usuario.core.gateway;

import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Usuario salvar(Usuario usuario);

    boolean existeUsuarioComEmail(String email);

    boolean existeUsuarioComLogin(String login);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorLogin(String login);

    List<Usuario> buscarTodos();

    List<Usuario> buscarPorNome(String nome);

    void atualizar(Usuario usuarioAtualizado);

    void remover(Long id);
}