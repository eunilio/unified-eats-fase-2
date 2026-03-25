package br.com.fiap.unifiedeats2.restaurante.infra.database.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.infra.database.entity.RestauranteEntity;

public final class RestauranteEntityMapper {

    private RestauranteEntityMapper() {
    }

    public static RestauranteEntity toEntity(Restaurante restaurante) {
        return new RestauranteEntity(
                restaurante.id(),
                restaurante.nome(),
                restaurante.endereco().cep(),
                restaurante.endereco().logradouro(),
                restaurante.endereco().numero(),
                restaurante.endereco().complemento(),
                restaurante.endereco().bairro(),
                restaurante.endereco().cidade(),
                restaurante.endereco().estado(),
                restaurante.tipoCozinha(),
                restaurante.horarioFuncionamento(),
                restaurante.donoId()
        );
    }

    public static Restaurante toDomain(RestauranteEntity entity) {
        Endereco endereco = new Endereco(
                entity.getCep(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado()
        );

        return new Restaurante(
                entity.getId(),
                entity.getNome(),
                endereco,
                entity.getTipoCozinha(),
                entity.getHorarioFuncionamento(),
                entity.getDonoId()
        );
    }
}