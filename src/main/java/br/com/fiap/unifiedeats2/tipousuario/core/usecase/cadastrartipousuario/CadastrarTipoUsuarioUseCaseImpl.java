package br.com.fiap.unifiedeats2.tipousuario.core.usecase.cadastrartipousuario;

import br.com.fiap.unifiedeats2.tipousuario.core.domain.TipoUsuario;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.CadastrarTipoUsuarioInputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.dto.TipoUsuarioOutputDTO;
import br.com.fiap.unifiedeats2.tipousuario.core.gateway.TipoUsuarioGateway;
import br.com.fiap.unifiedeats2.tipousuario.core.mapper.TipoUsuarioOutputMapper;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.DadoDuplicadoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarTipoUsuarioUseCaseImpl implements CadastrarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    @Override
    public TipoUsuarioOutputDTO run(CadastrarTipoUsuarioInputDTO input) {
        if (tipoUsuarioGateway.existePorNome(input.nome())) {
            throw new DadoDuplicadoException("Já existe tipo de usuário com nome informado.");
        }

        TipoUsuario tipoUsuario = new TipoUsuario(null, input.nome());
        TipoUsuario salvo = tipoUsuarioGateway.salvar(tipoUsuario);

        return TipoUsuarioOutputMapper.paraOutput(salvo);
    }
}