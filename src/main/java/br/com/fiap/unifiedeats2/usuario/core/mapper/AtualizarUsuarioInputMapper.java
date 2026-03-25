package br.com.fiap.unifiedeats2.usuario.core.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.usuario.core.dto.AtualizarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.AtualizarUsuarioRequestJson;

public final class AtualizarUsuarioInputMapper {

    private AtualizarUsuarioInputMapper() {
    }

    public static AtualizarUsuarioInputDTO toInput(Long id, AtualizarUsuarioRequestJson atualizarUsuarioRequestJson) {
        return new AtualizarUsuarioInputDTO(
                id,
                atualizarUsuarioRequestJson.nome(),
                atualizarUsuarioRequestJson.email(),
                atualizarUsuarioRequestJson.login(),
                new Endereco(
                        atualizarUsuarioRequestJson.endereco().cep(),
                        atualizarUsuarioRequestJson.endereco().logradouro(),
                        atualizarUsuarioRequestJson.endereco().numero(),
                        atualizarUsuarioRequestJson.endereco().complemento(),
                        atualizarUsuarioRequestJson.endereco().bairro(),
                        atualizarUsuarioRequestJson.endereco().cidade(),
                        atualizarUsuarioRequestJson.endereco().estado()
                )
        );
    }
}
