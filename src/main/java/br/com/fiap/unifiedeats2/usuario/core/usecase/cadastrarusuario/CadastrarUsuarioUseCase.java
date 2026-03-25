package br.com.fiap.unifiedeats2.usuario.core.usecase.cadastrarusuario;

import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.CadastrarUsuarioOutputDTO;

public interface CadastrarUsuarioUseCase {
    CadastrarUsuarioOutputDTO run(CadastrarUsuarioInputDTO input);
}
