package br.com.fiap.unifiedeats2.usuario.core.usecase.alterarsenhaUsuario;

import br.com.fiap.unifiedeats2.usuario.core.dto.AlterarSenhaUsuarioInputDTO;

public interface AlterarSenhaUsuarioUseCase {
    void run(AlterarSenhaUsuarioInputDTO input);
}