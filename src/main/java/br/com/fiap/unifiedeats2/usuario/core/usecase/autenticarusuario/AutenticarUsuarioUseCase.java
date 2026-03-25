package br.com.fiap.unifiedeats2.usuario.core.usecase.autenticarusuario;

import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioInputDTO;
import br.com.fiap.unifiedeats2.usuario.core.dto.AutenticarUsuarioOutputDTO;

public interface AutenticarUsuarioUseCase {
    AutenticarUsuarioOutputDTO executar(AutenticarUsuarioInputDTO input);
}