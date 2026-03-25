package br.com.fiap.unifiedeats2.restaurante.core.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.dto.AtualizarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.infra.web.json.AtualizarRestauranteRequestJson;

public final class AtualizarRestauranteInputMapper {

    private AtualizarRestauranteInputMapper() {
    }

    public static AtualizarRestauranteInputDTO toInput(Long id, AtualizarRestauranteRequestJson json) {
        return new AtualizarRestauranteInputDTO(
                id,
                json.nome(),
                new Endereco(
                        json.endereco().cep(),
                        json.endereco().logradouro(),
                        json.endereco().numero(),
                        json.endereco().complemento(),
                        json.endereco().bairro(),
                        json.endereco().cidade(),
                        json.endereco().estado()
                ),
                json.tipoCozinha(),
                json.horarioFuncionamento(),
                json.donoId()
        );
    }
}