package br.com.fiap.unifiedeats2.compartilhado.core.exception;

public class ParametroInvalidoException extends RuntimeException {

    private final String parametroNome;

    public ParametroInvalidoException(String parametroNome, String mensagem) {
        super(mensagem);
        this.parametroNome = parametroNome;
    }

    public String getParametroNome() {
        return parametroNome;
    }
}