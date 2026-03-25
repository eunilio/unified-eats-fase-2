package br.com.fiap.unifiedeats2.tipousuario.infra.config;

import br.com.fiap.unifiedeats2.tipousuario.core.controller.*;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import br.com.fiap.unifiedeats2.tipousuario.core.usecase.atualizartipousuario.*;
import br.com.fiap.unifiedeats2.tipousuario.core.usecase.buscartipousuarioporid.*;
import br.com.fiap.unifiedeats2.tipousuario.core.usecase.cadastrartipousuario.*;
import br.com.fiap.unifiedeats2.tipousuario.core.usecase.listartiposusuario.*;
import br.com.fiap.unifiedeats2.tipousuario.core.usecase.removertipousuario.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TipoUsuarioConfiguration {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public TipoUsuarioConfiguration(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    @Bean
    public CadastrarTipoUsuarioUseCase cadastrarTipoUsuarioUseCase() {
        return new CadastrarTipoUsuarioUseCaseImpl(tipoUsuarioGateway);
    }

    @Bean
    public CadastrarTipoUsuarioController cadastrarTipoUsuarioController(
            CadastrarTipoUsuarioUseCase cadastrarTipoUsuarioUseCase
    ) {
        return new CadastrarTipoUsuarioController(cadastrarTipoUsuarioUseCase);
    }

    @Bean
    public BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase() {
        return new BuscarTipoUsuarioPorIdUseCaseImpl(tipoUsuarioGateway);
    }

    @Bean
    public BuscarTipoUsuarioPorIdController buscarTipoUsuarioPorIdController(
            BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase
    ) {
        return new BuscarTipoUsuarioPorIdController(buscarTipoUsuarioPorIdUseCase);
    }

    @Bean
    public ListarTiposUsuarioUseCase listarTiposUsuarioUseCase() {
        return new ListarTiposUsuarioUseCaseImpl(tipoUsuarioGateway);
    }

    @Bean
    public ListarTiposUsuarioController listarTiposUsuarioController(
            ListarTiposUsuarioUseCase listarTiposUsuarioUseCase
    ) {
        return new ListarTiposUsuarioController(listarTiposUsuarioUseCase);
    }

    @Bean
    public AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase() {
        return new AtualizarTipoUsuarioUseCaseImpl(tipoUsuarioGateway);
    }

    @Bean
    public AtualizarTipoUsuarioController atualizarTipoUsuarioController(
            AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase
    ) {
        return new AtualizarTipoUsuarioController(atualizarTipoUsuarioUseCase);
    }

    @Bean
    public RemoverTipoUsuarioUseCase removerTipoUsuarioUseCase() {
        return new RemoverTipoUsuarioUseCaseImpl(tipoUsuarioGateway);
    }

    @Bean
    public RemoverTipoUsuarioController removerTipoUsuarioController(
            RemoverTipoUsuarioUseCase removerTipoUsuarioUseCase
    ) {
        return new RemoverTipoUsuarioController(removerTipoUsuarioUseCase);
    }
}