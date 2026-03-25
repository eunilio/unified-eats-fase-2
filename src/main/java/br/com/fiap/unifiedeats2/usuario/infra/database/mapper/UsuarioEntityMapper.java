package br.com.fiap.unifiedeats2.usuario.infra.database.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.compartilhado.infra.database.entity.EnderecoEntity;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;

import br.com.fiap.unifiedeats2.tipousuario.infra.database.entity.TipoUsuarioEntity;
import br.com.fiap.unifiedeats2.usuario.infra.database.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityMapper {

    public UsuarioEntity toEntity(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.id());
        entity.setNome(usuario.nome());
        entity.setEmail(usuario.email());
        entity.setLogin(usuario.login());
        entity.setSenha(usuario.senha());
        entity.setUltimaAtualizacao(usuario.ultimaAtualizacao());
        entity.setEndereco(toEmbeddable(usuario.endereco()));
        entity.setTipoUsuarios(
                usuario.tipoUsuarios().stream()
                        .map(this::toTipoUsuarioEntity)
                        .toList()
        );
        return entity;
    }

    public Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getLogin(),
                entity.getSenha(),
                toEndereco(entity.getEndereco()),
                entity.getTipoUsuarios().stream().map(this::toTipoUsuario).toList()
        );
    }

    private EnderecoEntity toEmbeddable(Endereco endereco) {
        EnderecoEntity embeddable = new EnderecoEntity();
        embeddable.setCep(endereco.cep());
        embeddable.setLogradouro(endereco.logradouro());
        embeddable.setNumero(endereco.numero());
        embeddable.setComplemento(endereco.complemento());
        embeddable.setBairro(endereco.bairro());
        embeddable.setCidade(endereco.cidade());
        embeddable.setEstado(endereco.estado());
        return embeddable;
    }

    private Endereco toEndereco(EnderecoEntity embeddable) {
        return new Endereco(
                embeddable.getCep(),
                embeddable.getLogradouro(),
                embeddable.getNumero(),
                embeddable.getComplemento(),
                embeddable.getBairro(),
                embeddable.getCidade(),
                embeddable.getEstado()
        );
    }

    private TipoUsuarioEntity toTipoUsuarioEntity(TipoUsuario tipoUsuario) {
        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        entity.setId(tipoUsuario.id());
        entity.setNome(tipoUsuario.nome());
        return entity;
    }

    private TipoUsuario toTipoUsuario(TipoUsuarioEntity entity) {
        return new TipoUsuario(entity.getId(), entity.getNome());
    }
}