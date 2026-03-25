package br.com.fiap.unifiedeats2.restaurante.core.usecase.cadastrarrestaurante;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.ParametroInvalidoException;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.restaurante.core.domain.Restaurante;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarRestauranteUseCaseImpl implements CadastrarRestauranteUseCase {

    private static final String TIPO_DONO_RESTAURANTE = "DONO_RESTAURANTE";

    private final RestauranteGateway restauranteGateway;
    private final UsuarioGateway usuarioGateway;

    @Override
    public CadastrarRestauranteOutputDTO run(CadastrarRestauranteInputDTO input) {
        Usuario dono = obterDonoValido(input.donoId());

        Restaurante restaurante = new Restaurante(
                null,
                input.nome(),
                input.endereco(),
                input.tipoCozinha(),
                input.horarioFuncionamento(),
                dono.id()
        );

        Restaurante restauranteSalvo = restauranteGateway.salvar(restaurante);
        return new CadastrarRestauranteOutputDTO(restauranteSalvo.id());
    }

    private Usuario obterDonoValido(Long donoId) {
        if (donoId == null) {
            throw new ParametroInvalidoException("donoId", "O dono do restaurante é obrigatório.");
        }

        Usuario usuario = usuarioGateway.buscarPorId(donoId)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário dono do restaurante não encontrado.")
                );

        boolean ehDono = usuario.tipoUsuarios().stream()
                .anyMatch(tipo -> TIPO_DONO_RESTAURANTE.equalsIgnoreCase(tipo.nome()));

        if (!ehDono) {
            throw new ParametroInvalidoException(
                    "donoId",
                    "O usuário informado não possui perfil de dono de restaurante."
            );
        }

        return usuario;
    }
}