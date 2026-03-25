package br.com.fiap.unifiedeats2.usuario.core.exception;

public class ParametroInvalidoException extends RuntimeException {
    private final String parametroNome;

    public ParametroInvalidoException(String parametroNome, String message) {
        super(message);
        this.parametroNome = parametroNome;
    }

    public String getParametroNome() {
        return parametroNome;
    }
}