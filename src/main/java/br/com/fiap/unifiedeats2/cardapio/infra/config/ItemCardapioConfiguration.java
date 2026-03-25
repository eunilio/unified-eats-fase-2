package br.com.fiap.unifiedeats2.cardapio.infra.config;

import br.com.fiap.unifiedeats2.cardapio.core.controller.AtualizarItemCardapioController;
import br.com.fiap.unifiedeats2.cardapio.core.controller.BuscarItemCardapioPorIdController;
import br.com.fiap.unifiedeats2.cardapio.core.controller.CadastrarItemCardapioController;
import br.com.fiap.unifiedeats2.cardapio.core.controller.ListarItensCardapioController;
import br.com.fiap.unifiedeats2.cardapio.core.controller.RemoverItemCardapioController;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.atualizaritemcardapio.AtualizarItemCardapioUseCase;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.atualizaritemcardapio.AtualizarItemCardapioUseCaseImpl;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.buscaritemcardapioporid.BuscarItemCardapioPorIdUseCase;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.buscaritemcardapioporid.BuscarItemCardapioPorIdUseCaseImpl;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.cadastraritemcardapio.CadastrarItemCardapioUseCase;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.cadastraritemcardapio.CadastrarItemCardapioUseCaseImpl;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.listaritenscardapio.ListarItensCardapioUseCase;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.listaritenscardapio.ListarItensCardapioUseCaseImpl;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.removeritemcardapio.RemoverItemCardapioUseCase;
import br.com.fiap.unifiedeats2.cardapio.core.usecase.removeritemcardapio.RemoverItemCardapioUseCaseImpl;
import br.com.fiap.unifiedeats2.cardapio.infra.gateway.ItemCardapioJpaGateway;
import br.com.fiap.unifiedeats2.cardapio.infra.database.repository.ItemCardapioRepository;
import br.com.fiap.unifiedeats2.restaurante.core.gateway.RestauranteGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCardapioConfiguration {

    @Bean
    public ItemCardapioGateway itemCardapioGateway(ItemCardapioRepository itemCardapioRepository) {
        return new ItemCardapioJpaGateway(itemCardapioRepository);
    }

    @Bean
    public CadastrarItemCardapioUseCase cadastrarItemCardapioUseCase(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        return new CadastrarItemCardapioUseCaseImpl(itemCardapioGateway, restauranteGateway);
    }

    @Bean
    public BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        return new BuscarItemCardapioPorIdUseCaseImpl(itemCardapioGateway, restauranteGateway);
    }

    @Bean
    public ListarItensCardapioUseCase listarItensCardapioUseCase(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        return new ListarItensCardapioUseCaseImpl(itemCardapioGateway, restauranteGateway);
    }

    @Bean
    public AtualizarItemCardapioUseCase atualizarItemCardapioUseCase(
            ItemCardapioGateway itemCardapioGateway,
            RestauranteGateway restauranteGateway
    ) {
        return new AtualizarItemCardapioUseCaseImpl(itemCardapioGateway, restauranteGateway);
    }

    @Bean
    public RemoverItemCardapioUseCase removerItemCardapioUseCase(
            ItemCardapioGateway itemCardapioGateway
    ) {
        return new RemoverItemCardapioUseCaseImpl(itemCardapioGateway);
    }

    @Bean
    public CadastrarItemCardapioController cadastrarItemCardapioController(
            CadastrarItemCardapioUseCase cadastrarItemCardapioUseCase
    ) {
        return new CadastrarItemCardapioController(cadastrarItemCardapioUseCase);
    }

    @Bean
    public BuscarItemCardapioPorIdController buscarItemCardapioPorIdController(
            BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase
    ) {
        return new BuscarItemCardapioPorIdController(buscarItemCardapioPorIdUseCase);
    }

    @Bean
    public ListarItensCardapioController listarItensCardapioController(
            ListarItensCardapioUseCase listarItensCardapioUseCase
    ) {
        return new ListarItensCardapioController(listarItensCardapioUseCase);
    }

    @Bean
    public AtualizarItemCardapioController atualizarItemCardapioController(
            AtualizarItemCardapioUseCase atualizarItemCardapioUseCase
    ) {
        return new AtualizarItemCardapioController(atualizarItemCardapioUseCase);
    }

    @Bean
    public RemoverItemCardapioController removerItemCardapioController(
            RemoverItemCardapioUseCase removerItemCardapioUseCase
    ) {
        return new RemoverItemCardapioController(removerItemCardapioUseCase);
    }
}