package br.com.fiap.unifiedeats2.restaurante.core.usecase.atualizarrestaurante;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.dto.AtualizarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;

public class AtualizarRestauranteUseCaseImpl implements AtualizarRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;
    private final UsuarioGateway usuarioGateway;

    public AtualizarRestauranteUseCaseImpl(
            RestauranteGateway restauranteGateway,
            UsuarioGateway usuarioGateway
    ) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void run(AtualizarRestauranteInputDTO input) {
        validarExistenciaRestaurante(input.id());
        validarDono(input.donoId());

        Restaurante restaurante = new Restaurante(
                input.id(),
                input.nome(),
                input.endereco(),
                input.tipoCozinha(),
                input.horarioFuncionamento(),
                input.donoId()
        );

        restauranteGateway.atualizar(restaurante);
    }

    private void validarExistenciaRestaurante(Long id) {
        if (id == null || restauranteGateway.buscarPorId(id).isEmpty()) {
            throw new RecursoNaoEncontradoException("Restaurante não encontrado.");
        }
    }

    private void validarDono(Long donoId) {
        if (donoId == null || usuarioGateway.buscarPorId(donoId).isEmpty()) {
            throw new RecursoNaoEncontradoException("Usuário dono do restaurante não encontrado.");
        }
    }
}