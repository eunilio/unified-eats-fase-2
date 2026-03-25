package br.com.fiap.unifiedeats2.tipousuario.infra.database;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import br.com.fiap.unifiedeats2.tipousuario.infra.database.entity.TipoUsuarioEntity;
import br.com.fiap.unifiedeats2.tipousuario.infra.database.mapper.TipoUsuarioEntityMapper;
import br.com.fiap.unifiedeats2.tipousuario.infra.database.repository.TipoUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TipoUsuarioJpaGateway implements TipoUsuarioGateway {

    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final TipoUsuarioEntityMapper tipoUsuarioEntityMapper;

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {
        TipoUsuarioEntity entity = tipoUsuarioEntityMapper.toEntity(tipoUsuario);
        TipoUsuarioEntity salvo = tipoUsuarioRepository.save(entity);
        return tipoUsuarioEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<TipoUsuario> buscarPorId(Long id) {
        return tipoUsuarioRepository.findById(id)
                .map(tipoUsuarioEntityMapper::toDomain);
    }

    @Override
    public List<TipoUsuario> buscarTodos() {
        return tipoUsuarioRepository.findAll().stream()
                .map(tipoUsuarioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existePorNome(String nome) {
        return tipoUsuarioRepository.existsByNomeIgnoreCase(nome);
    }

    @Override
    public void atualizar(TipoUsuario tipoUsuario) {
        TipoUsuarioEntity entity = tipoUsuarioEntityMapper.toEntity(tipoUsuario);
        tipoUsuarioRepository.save(entity);
    }

    @Override
    public void remover(Long id) {
        tipoUsuarioRepository.deleteById(id);
    }
}