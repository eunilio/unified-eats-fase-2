package br.com.fiap.unifiedeats2.compartilhado.core.valueobject;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Endereco {

    private final String cep;
    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String estado;

    public Endereco(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String estado) {

        this.cep = validaCampoObrigatorio(cep, "CEP inválido");
        this.logradouro = validaCampoObrigatorio(logradouro, "Logradouro inválido");
        this.numero = validaCampoObrigatorio(numero, "Número inválido");
        this.complemento = complemento != null ? complemento.trim() : null;
        this.bairro = validaCampoObrigatorio(bairro, "Bairro inválido");
        this.cidade = validaCampoObrigatorio(cidade, "Cidade inválida");
        this.estado = validaCampoObrigatorio(estado, "Estado inválido").toUpperCase();
    }

    public String cep() {
        return cep;
    }

    public String logradouro() {
        return logradouro;
    }

    public String numero() {
        return numero;
    }

    public String complemento() {
        return complemento;
    }

    public String bairro() {
        return bairro;
    }

    public String cidade() {
        return cidade;
    }

    public String estado() {
        return estado;
    }

    private String validaCampoObrigatorio(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
        return valor.trim();
    }
}