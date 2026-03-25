package br.com.fiap.unifiedeats2.restaurante.infra.config;

import br.com.fiap.unifiedeats2.restaurante.core.controller.AtualizarRestauranteController;
import br.com.fiap.unifiedeats2.restaurante.core.controller.BuscarRestaurantePorIdController;
import br.com.fiap.unifiedeats2.restaurante.core.controller.CadastrarRestauranteController;
import br.com.fiap.unifiedeats2.restaurante.core.controller.ListarRestaurantesController;
import br.com.fiap.unifiedeats2.restaurante.core.controller.RemoverRestauranteController;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.atualizarrestaurante.AtualizarRestauranteUseCase;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.atualizarrestaurante.AtualizarRestauranteUseCaseImpl;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.buscarrestauranteporid.BuscarRestaurantePorIdUseCase;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.buscarrestauranteporid.BuscarRestaurantePorIdUseCaseImpl;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.cadastrarrestaurante.CadastrarRestauranteUseCase;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.cadastrarrestaurante.CadastrarRestauranteUseCaseImpl;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.listarrestaurantes.ListarRestaurantesUseCase;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.listarrestaurantes.ListarRestaurantesUseCaseImpl;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.removerrestaurante.RemoverRestauranteUseCase;
import br.com.fiap.unifiedeats2.restaurante.core.usecase.removerrestaurante.RemoverRestauranteUseCaseImpl;
import br.com.fiap.unifiedeats2.restaurante.infra.gateway.RestauranteJpaGateway;
import br.com.fiap.unifiedeats2.restaurante.infra.database.repository.RestauranteRepository;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestauranteConfiguration {

    @Bean
    public RestauranteGateway restauranteGateway(RestauranteRepository restauranteRepository) {
        return new RestauranteJpaGateway(restauranteRepository);
    }

    @Bean
    public CadastrarRestauranteUseCase cadastrarRestauranteUseCase(
            RestauranteGateway restauranteGateway,
            UsuarioGateway usuarioGateway
    ) {
        return new CadastrarRestauranteUseCaseImpl(restauranteGateway, usuarioGateway);
    }

    @Bean
    public BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase(
            RestauranteGateway restauranteGateway
    ) {
        return new BuscarRestaurantePorIdUseCaseImpl(restauranteGateway);
    }

    @Bean
    public ListarRestaurantesUseCase listarRestaurantesUseCase(
            RestauranteGateway restauranteGateway
    ) {
        return new ListarRestaurantesUseCaseImpl(restauranteGateway);
    }

    @Bean
    public AtualizarRestauranteUseCase atualizarRestauranteUseCase(
            RestauranteGateway restauranteGateway,
            UsuarioGateway usuarioGateway
    ) {
        return new AtualizarRestauranteUseCaseImpl(restauranteGateway, usuarioGateway);
    }

    @Bean
    public RemoverRestauranteUseCase removerRestauranteUseCase(
            RestauranteGateway restauranteGateway
    ) {
        return new RemoverRestauranteUseCaseImpl(restauranteGateway);
    }

    @Bean
    public CadastrarRestauranteController cadastrarRestauranteController(
            CadastrarRestauranteUseCase cadastrarRestauranteUseCase
    ) {
        return new CadastrarRestauranteController(cadastrarRestauranteUseCase);
    }

    @Bean
    public BuscarRestaurantePorIdController buscarRestaurantePorIdController(
            BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase
    ) {
        return new BuscarRestaurantePorIdController(buscarRestaurantePorIdUseCase);
    }

    @Bean
    public ListarRestaurantesController listarRestaurantesController(
            ListarRestaurantesUseCase listarRestaurantesUseCase
    ) {
        return new ListarRestaurantesController(listarRestaurantesUseCase);
    }

    @Bean
    public AtualizarRestauranteController atualizarRestauranteController(
            AtualizarRestauranteUseCase atualizarRestauranteUseCase
    ) {
        return new AtualizarRestauranteController(atualizarRestauranteUseCase);
    }

    @Bean
    public RemoverRestauranteController removerRestauranteController(
            RemoverRestauranteUseCase removerRestauranteUseCase
    ) {
        return new RemoverRestauranteController(removerRestauranteUseCase);
    }
}