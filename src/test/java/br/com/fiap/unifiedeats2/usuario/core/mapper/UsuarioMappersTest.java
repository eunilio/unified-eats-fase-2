package br.com.fiap.unifiedeats2.usuario.core.mapper;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;
import br.com.fiap.unifiedeats2.compartilhado.infra.web.json.EnderecoRequestJson;
import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.usuario.core.domain.Usuario;
import br.com.fiap.unifiedeats2.usuario.core.dto.AtualizarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.UsuarioOutputDTO;
import br.com.fiap.unifiedeats2.usuario.infra.web.json.AtualizarUsuarioRequestJson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMappersTest {

    @Test
    void deveMapearAtualizarUsuarioInput() {
        AtualizarUsuarioRequestJson json = new AtualizarUsuarioRequestJson(
                "Felipe",
                "felipe@email.com",
                "felipe.login",
                new EnderecoRequestJson("03450000", "Rua A", "10", "Apto", "Centro", "São Paulo", "sp")
        );

        AtualizarUsuarioInputDTO input = AtualizarUsuarioInputMapper.toInput(7L, json);

        assertEquals(7L, input.id());
        assertEquals("Felipe", input.nome());
        assertEquals("SP", input.endereco().estado());
    }

    @Test
    void deveMapearUsuarioParaOutputDeCadastroEConsulta() {
        Usuario usuario = new Usuario(1L, "Felipe", "felipe@email.com", "felipe.login", "12345",
                new Endereco("03450000", "Rua A", "10", "Apto", "Centro", "São Paulo", "SP"),
                List.of(new TipoUsuario(1L, "CLIENTE")));

        CadastrarUsuarioOutputDTO cadastroOutput = UsuarioOutputMapper.paraOutput(usuario);
        UsuarioOutputDTO buscaOutput = BuscarUsuarioPorIdOutputMapper.paraOutput(usuario);
        UsuarioOutputDTO listaOutput = ListarUsuariosOutputMapper.paraOutput(usuario);

        assertEquals(1L, cadastroOutput.id());
        assertEquals("Felipe", buscaOutput.nome());
        assertEquals(List.of("CLIENTE"), buscaOutput.tipoUsuarios());
        assertEquals(buscaOutput, listaOutput);
    }
}
