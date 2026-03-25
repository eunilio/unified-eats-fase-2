package br.com.fiap.unifiedeats2.usuario.core.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.dto.EnderecoOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;

public final class BuscarUsuarioPorIdOutputMapper {

    private BuscarUsuarioPorIdOutputMapper() {
    }

    public static UsuarioOutputDTO paraOutput(Usuario usuario) {
        return new UsuarioOutputDTO(
                usuario.id(),
                usuario.nome(),
                usuario.email(),
                usuario.login(),
                new EnderecoOutputDTO(
                        usuario.endereco().cep(),
                        usuario.endereco().logradouro(),
                        usuario.endereco().numero(),
                        usuario.endereco().complemento(),
                        usuario.endereco().bairro(),
                        usuario.endereco().cidade(),
                        usuario.endereco().estado()
                ),
                usuario.tipoUsuarios().stream()
                        .map(tipoUsuario -> tipoUsuario.nome())
                        .toList()
        );
    }
}