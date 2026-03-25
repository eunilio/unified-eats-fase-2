package br.com.fiap.unifiedeats2.usuario.infra.database;

import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.infra.database.entity.UsuarioEntity;
import br.com.fiap.unifiedeats2.usuario.infra.database.mapper.UsuarioEntityMapper;
import br.com.fiap.unifiedeats2.usuario.infra.database.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioJpaGateway implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntity entity = usuarioEntityMapper.toEntity(usuario);
        UsuarioEntity salvo = usuarioRepository.save(entity);
        return usuarioEntityMapper.toDomain(salvo);
    }

    @Override
    public boolean existeUsuarioComEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public boolean existeUsuarioComLogin(String login) {
        return usuarioRepository.existsByLogin(login);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioEntityMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        return usuarioRepository.findByLogin(login)
                .map(usuarioEntityMapper::toDomain);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuarioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(usuarioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void atualizar(Usuario usuario) {
        UsuarioEntity entity = usuarioEntityMapper.toEntity(usuario);
        usuarioRepository.save(entity);
    }

    @Override
    public void remover(Long id) {
        usuarioRepository.deleteById(id);
    }
}