package br.com.fiap.unifiedeats2.tipousuario.core.usecase.atualizartipousuario;

import br.com.fiap.unifiedeats2.tipousuario.core.dto.AtualizarTipoUsuarioInputDTO;

public interface AtualizarTipoUsuarioUseCase {
    void run(AtualizarTipoUsuarioInputDTO input);
}