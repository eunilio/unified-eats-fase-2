package br.com.fiap.unifiedeats2.usuario.infra.web;

import br.com.fiap.unifiedeats2.compartilhado.core.dto.EnderecoOutputDTO;
import br.com.fiap.unifiedeats2.compartilhado.infra.web.json.EnderecoRequestJson;
import br.com.fiap.unifiedeats2.tipousuario.infra.web.json.TipoUsuarioRefJson;
import br.com.fiap.unifiedeats2.usuario.core.controller.*;
import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.AlterarSenhaRequestJson;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.AtualizarUsuarioRequestJson;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.CadastrarUsuarioRequestJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsurioApiControllerTest {

    private CadastrarUsuarioController cadastrarUsuarioController;
    private BuscarUsuarioPorIdController buscarUsuarioPorIdController;
    private ListarUsuariosController listarUsuariosController;
    private BuscarUsuariosPorNomeController buscarUsuariosPorNomeController;
    private AtualizarUsuarioController atualizarUsuarioController;
    private RemoverUsuarioController removerUsuarioController;
    private AlterarSenhaUsuarioController alterarSenhaUsuarioController;

    private UsuarioApiController controller;

    @BeforeEach
    void setUp() {
        cadastrarUsuarioController = mock(CadastrarUsuarioController.class);
        buscarUsuarioPorIdController = mock(BuscarUsuarioPorIdController.class);
        listarUsuariosController = mock(ListarUsuariosController.class);
        buscarUsuariosPorNomeController = mock(BuscarUsuariosPorNomeController.class);
        atualizarUsuarioController = mock(AtualizarUsuarioController.class);
        removerUsuarioController = mock(RemoverUsuarioController.class);
        alterarSenhaUsuarioController = mock(AlterarSenhaUsuarioController.class);

        controller = new UsuarioApiController(
                cadastrarUsuarioController,
                buscarUsuarioPorIdController,
                listarUsuariosController,
                buscarUsuariosPorNomeController,
                atualizarUsuarioController,
                removerUsuarioController,
                alterarSenhaUsuarioController
        );
    }

    @Test
    void deveListarUsuarios() {
        List<UsuarioOutputDTO> output = List.of(usuarioOutput());
        when(listarUsuariosController.listar()).thenReturn(output);

        ResponseEntity<List<UsuarioOutputDTO>> response = controller.listar();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }

    @Test
    void deveBuscarUsuarioPorId() {
        UsuarioOutputDTO output = usuarioOutput();
        when(buscarUsuarioPorIdController.buscarPorId(1L)).thenReturn(output);

        ResponseEntity<UsuarioOutputDTO> response = controller.buscarPorId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }

    @Test
    void deveBuscarUsuariosPorNome() {
        List<UsuarioOutputDTO> output = List.of(usuarioOutput());
        when(buscarUsuariosPorNomeController.buscarPorNome("Felipe")).thenReturn(output);

        ResponseEntity<List<UsuarioOutputDTO>> response = controller.buscarPorNome("Felipe");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }

    @Test
    void deveCadastrarUsuarioComLocation() {
        when(cadastrarUsuarioController.cadastrar(any())).thenReturn(10L);

        CadastrarUsuarioRequestJson json = new CadastrarUsuarioRequestJson(
                "Usuario Teste",
                "usuario@email.com",
                "usuario.login",
                "12345",
                enderecoRequest(),
                List.of(new TipoUsuarioRefJson(1L), new TipoUsuarioRefJson(null))
        );

        ResponseEntity<Void> response = controller.cadastrar(json);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getHeaders().getLocation());
        assertEquals("/v1/usuarios/10", response.getHeaders().getLocation().toString());

        ArgumentCaptor<br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioInputDTO> captor =
                ArgumentCaptor.forClass(br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioInputDTO.class);

        verify(cadastrarUsuarioController).cadastrar(captor.capture());

        var input = captor.getValue();
        assertEquals("Usuario Teste", input.nome());
        assertEquals("usuario@email.com", input.email());
        assertEquals("usuario.login", input.login());
        assertEquals("12345", input.senha());
        assertEquals("03450000", input.cep());
        assertEquals("Rua A", input.logradouro());
        assertEquals("10", input.numero());
        assertEquals("Apto 1", input.complemento());
        assertEquals("Centro", input.bairro());
        assertEquals("São Paulo", input.cidade());
        assertEquals("SP", input.estado());
        assertEquals(List.of(1L), input.tiposUsuario());
    }

    @Test
    void deveAtualizarUsuario() {
        AtualizarUsuarioRequestJson json = new AtualizarUsuarioRequestJson(
                "Usuario Atualizado",
                "novo@email.com",
                "novo.login",
                enderecoRequest()
        );

        ResponseEntity<Void> response = controller.atualizar(5L, json);

        assertEquals(204, response.getStatusCode().value());

        ArgumentCaptor<br.com.fiap.unifiedeats2.usuario.core.dto.AtualizarUsuarioInputDTO> captor =
                ArgumentCaptor.forClass(br.com.fiap.unifiedeats2.usuario.core.dto.AtualizarUsuarioInputDTO.class);

        verify(atualizarUsuarioController).atualizar(captor.capture());

        var input = captor.getValue();
        assertEquals(5L, input.id());
        assertEquals("Usuario Atualizado", input.nome());
        assertEquals("novo@email.com", input.email());
        assertEquals("novo.login", input.login());
        assertEquals("03450000", input.endereco().cep());
    }

    @Test
    void deveAlterarSenha() {
        AlterarSenhaRequestJson json = new AlterarSenhaRequestJson("senhaAtual", "novaSenha");

        ResponseEntity<Void> response = controller.alterarSenha(7L, json);

        assertEquals(204, response.getStatusCode().value());

        ArgumentCaptor<br.com.fiap.unifiedeats2.usuario.core.dto.AlterarSenhaUsuarioInputDTO> captor =
                ArgumentCaptor.forClass(br.com.fiap.unifiedeats2.usuario.core.dto.AlterarSenhaUsuarioInputDTO.class);

        verify(alterarSenhaUsuarioController).alterarSenha(captor.capture());

        var input = captor.getValue();
        assertEquals(7L, input.id());
        assertEquals("senhaAtual", input.senhaAtual());
        assertEquals("novaSenha", input.novaSenha());
    }

    @Test
    void deveDeletarUsuario() {
        ResponseEntity<Void> response = controller.deletar(9L);

        assertEquals(204, response.getStatusCode().value());
        verify(removerUsuarioController).remover(9L);
    }

    private UsuarioOutputDTO usuarioOutput() {
        return new UsuarioOutputDTO(
                1L,
                "Usuario Teste",
                "usuario@email.com",
                "usuario.login",
                new EnderecoOutputDTO("03450000", "Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP"),
                List.of("CLIENTE")
        );
    }

    private EnderecoRequestJson enderecoRequest() {
        return new EnderecoRequestJson("03450000", "Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP");
    }
}