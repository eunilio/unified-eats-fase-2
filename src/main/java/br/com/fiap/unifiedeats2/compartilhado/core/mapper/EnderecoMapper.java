package br.com.fiap.unifiedeats2.compartilhado.core.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.dto.EnderecoOutputDTO;
import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;

public final class EnderecoMapper {

    private EnderecoMapper() {
    }

    public static EnderecoOutputDTO paraOutputDTO(Endereco endereco) {
        return new EnderecoOutputDTO(
                endereco.cep(),
                endereco.logradouro(),
                endereco.numero(),
                endereco.complemento(),
                endereco.bairro(),
                endereco.cidade(),
                endereco.estado()
        );
    }
}