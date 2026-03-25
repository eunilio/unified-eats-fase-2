package br.com.fiap.unifiedeats2.tipousuario.core.gateway;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioGateway {

    TipoUsuario salvar(TipoUsuario tipoUsuario);

    Optional<TipoUsuario> buscarPorId(Long id);

    List<TipoUsuario> buscarTodos();

    boolean existePorNome(String nome);

    void atualizar(TipoUsuario tipoUsuario);

    void remover(Long id);
}