package br.com.fiap.unifiedeats2.restaurante.core.domain;

import br.com.fiap.unifiedeats2.compartilhado.core.valueobject.Endereco;

public record Restaurante(
        Long id,
        String nome,
        Endereco endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {

    public Restaurante {
        validarNome(nome);
        validarEndereco(endereco);
        validarTipoCozinha(tipoCozinha);
        validarHorarioFuncionamento(horarioFuncionamento);
        validarDono(donoId);
    }

    private static void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do restaurante é obrigatório.");
        }

        if (nome.trim().length() < 3) {
            throw new IllegalArgumentException("Nome do restaurante deve ter pelo menos 3 caracteres.");
        }
    }

    private static void validarEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço do restaurante é obrigatório.");
        }
    }

    private static void validarTipoCozinha(String tipoCozinha) {
        if (tipoCozinha == null || tipoCozinha.isBlank()) {
            throw new IllegalArgumentException("Tipo de cozinha é obrigatório.");
        }
    }

    private static void validarHorarioFuncionamento(String horarioFuncionamento) {
        if (horarioFuncionamento == null || horarioFuncionamento.isBlank()) {
            throw new IllegalArgumentException("Horário de funcionamento é obrigatório.");
        }
    }

    private static void validarDono(Long donoId) {
        if (donoId == null) {
            throw new IllegalArgumentException("Dono do restaurante é obrigatório.");
        }
    }
}