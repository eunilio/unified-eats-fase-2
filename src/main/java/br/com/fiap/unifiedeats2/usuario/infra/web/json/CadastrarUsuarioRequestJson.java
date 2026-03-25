package br.com.fiap.unifiedeats2.usuario.infra.web.json;

import br.com.fiap.unifiedeats2.compartilhado.infra.web.json.EnderecoRequestJson;
import br.com.fiap.unifiedeats2.tipousuario.infra.web.json.TipoUsuarioRefJson;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CadastrarUsuarioRequestJson(

        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String login,
        @NotBlank String senha,
        @Valid @NotNull EnderecoRequestJson endereco,
        @NotEmpty List<TipoUsuarioRefJson> tipoUsuarios

) {}
