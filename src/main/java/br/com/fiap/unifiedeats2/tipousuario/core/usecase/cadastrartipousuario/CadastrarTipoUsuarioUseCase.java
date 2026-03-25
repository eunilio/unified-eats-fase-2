package br.com.fiap.unifiedeats2.tipousuario.core.usecase.cadastrartipousuario;

import br.com.fiap.unifiedeats2.tipousuario.core.dto.CadastrarTipoUsuarioInputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;

public interface CadastrarTipoUsuarioUseCase {
    TipoUsuarioOutputDTO run(CadastrarTipoUsuarioInputDTO input);
}