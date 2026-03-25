package br.com.fiap.unifiedeats2.usuario.infra.config;

import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.core.controller.*;
import br.com.fiap.unifiedeats2.usuario.core.gateway.UsuarioGateway;
import br.com.fiap.unifiedeats2.usuario.core.usecase.alterarsenhaUsuario.AlterarSenhaUsuarioUseCase;
import br.com.fiap.unifiedeats2.usuario.core.usecase.alterarsenhaUsuario.AlterarSenhaUsuarioUseCaseImpl;
import br.com.fiap.unifiedeats2.usuario.core.usecase.atualizarusuario.AtualizarUsuarioUseCase;
import br.com.fiap.unifiedeats2.usuario.core.usecase.atualizarusuario.AtualizarUsuarioUseCaseImpl;
import br.com.fiap.unifiedeats2.usuario.core.usecase.autenticarusuario.AutenticarUsuarioUseCase;
import br.com.fiap.unifiedeats2.usuario.core.usecase.autenticarusuario.AutenticarUsuarioUseCaseImpl;
import br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuarioporid.BuscarUsuarioPorIdUseCase;
import br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuarioporid.BuscarUsuarioPorIdUseCaseImpl;
import br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuariospornome.BuscarUsuariosPorNomeUseCase;
import br.com.fiap.unifiedeats2.usuario.core.usecase.buscarusuariospornome.BuscarUsuariosPorNomeUseCaseImpl;
import br.com.fiap.unifiedeats2.usuario.core.usecase.cadastrarusuario.CadastrarUsuarioUseCase;
import br.com.fiap.unifiedeats2.usuario.core.usecase.cadastrarusuario.CadastrarUsuarioUseCaseImpl;
import br.com.fiap.unifiedeats2.usuario.core.usecase.listarusuarios.ListarUsuariosUseCase;
import br.com.fiap.unifiedeats2.usuario.core.usecase.listarusuarios.ListarUsuariosUseCaseImpl;
import br.com.fiap.unifiedeats2.usuario.core.usecase.removerusuario.RemoverUsuarioUseCase;
import br.com.fiap.unifiedeats2.usuario.core.usecase.removerusuario.RemoverUsuarioUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UsuarioConfiguration {

    private final UsuarioGateway usuarioGateway;
    private final TipoUsuarioGateway tipoUsuarioGateway;
    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase() {
        return new CadastrarUsuarioUseCaseImpl(usuarioGateway, tipoUsuarioGateway);
    }

    @Bean
    public CadastrarUsuarioController usuarioController(CadastrarUsuarioUseCase cadastrarUsuarioUseCase) {
        return new CadastrarUsuarioController(cadastrarUsuarioUseCase);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase(UsuarioGateway usuarioGateway) {
        return new BuscarUsuarioPorIdUseCaseImpl(usuarioGateway);
    }

    @Bean
    public BuscarUsuarioPorIdController buscarUsuarioPorIdController(BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
        return new BuscarUsuarioPorIdController(buscarUsuarioPorIdUseCase);
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(UsuarioGateway usuarioGateway) {
        return new ListarUsuariosUseCaseImpl(usuarioGateway);
    }

    @Bean
    public ListarUsuariosController listarUsuariosController(ListarUsuariosUseCase listarUsuariosUseCase) {
        return new ListarUsuariosController(listarUsuariosUseCase);
    }

    @Bean
    public BuscarUsuariosPorNomeUseCase buscarUsuariosPorNomeUseCase(UsuarioGateway usuarioGateway) {
        return new BuscarUsuariosPorNomeUseCaseImpl(usuarioGateway);
    }

    @Bean
    public BuscarUsuariosPorNomeController buscarUsuariosPorNomeController(
            BuscarUsuariosPorNomeUseCase buscarUsuariosPorNomeUseCase
    ) {
        return new BuscarUsuariosPorNomeController(buscarUsuariosPorNomeUseCase);
    }

    @Bean
    public AtualizarUsuarioUseCase atualizarUsuarioUseCase() {
        return new AtualizarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    public AtualizarUsuarioController atualizarUsuarioController(AtualizarUsuarioUseCase atualizarUsuarioUseCase) {
        return new AtualizarUsuarioController(atualizarUsuarioUseCase);
    }

    @Bean
    public AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase() {
        return new AlterarSenhaUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    public AlterarSenhaUsuarioController alterarSenhaUsuarioController(AlterarSenhaUsuarioUseCase alterarSenhaUsuarioUseCase) {
        return new AlterarSenhaUsuarioController(alterarSenhaUsuarioUseCase);
    }

    @Bean
    public RemoverUsuarioUseCase removerUsuarioUseCase() {
        return new RemoverUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    public RemoverUsuarioController removerUsuarioController(RemoverUsuarioUseCase removerUsuarioUseCase) {
        return new RemoverUsuarioController(removerUsuarioUseCase);
    }

    @Bean
    public AutenticarUsuarioUseCase autenticarUsuarioUseCase() {
        return new AutenticarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    public AutenticarUsuarioController autenticarUsuarioController(
            AutenticarUsuarioUseCase autenticarUsuarioUseCase
    ) {
        return new AutenticarUsuarioController(autenticarUsuarioUseCase);
    }
}
