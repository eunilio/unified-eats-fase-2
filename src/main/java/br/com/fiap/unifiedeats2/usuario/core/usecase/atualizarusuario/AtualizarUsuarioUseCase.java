package br.com.fiap.unifiedeats2.usuario.core.usecase.atualizarusuario;

import br.com.fiap.unifiedeats2.usuario.core.dto.AtualizarUsuarioInputDTO;

public interface AtualizarUsuarioUseCase {
    void  run(AtualizarUsuarioInputDTO input);
}