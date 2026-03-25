package br.com.fiap.unifiedeats2.compartilhado.infra.exception;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.DadoDuplicadoException;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.tipousuario.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.usuario.core.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ProblemDetail handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        problem.setTitle("Recurso não encontrado");

        problem.setType(URI.create("/errors/usuario-nao-encontrado"));
        problem.setInstance(URI.create(request.getRequestURI()));

        problem.setProperty("timestamp", OffsetDateTime.now());

        return problem;
    }

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        problem.setTitle("Erro interno");
        problem.setDetail("Ocorreu um erro inesperado");

        problem.setType(URI.create("/errors/internal-server-error"));
        problem.setInstance(URI.create(request.getRequestURI()));

        problem.setProperty("timestamp", OffsetDateTime.now());

        return problem;
    }

    @ExceptionHandler(SenhaAtualInvalidaException.class)
    public ProblemDetail handleSenhaAtualInvalidaException(SenhaAtualInvalidaException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        problemDetail.setTitle("Senha atual inválida");
        problemDetail.setType(URI.create("/errors/senha-atual-invalida"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", OffsetDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ProblemDetail handleCredenciaisInvalidasException(CredenciaisInvalidasException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        problemDetail.setTitle("Credenciais inválidas");
        problemDetail.setType(URI.create("/errors/credenciais-invalidas"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", OffsetDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(DadoDuplicadoException.class)
    public ProblemDetail handleEmailDuplicadoException(DadoDuplicadoException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());

        problemDetail.setTitle("Conflito de dados");
        problemDetail.setType(URI.create("/errors/email-duplicado"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", OffsetDateTime.now());

        return problemDetail;
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ProblemDetail handleInvalidCredentialsException(ParametroInvalidoException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Parâmetro inválido");

        problemDetail.setTitle("Erro de validação");
        problemDetail.setType(URI.create("/errors/parametro-invalido"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        Map<String, List<String>> errors = new HashMap<>();
        errors.put(e.getParametroNome(), List.of(e.getMessage()));

        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Campos inválidos na requisição");

        problemDetail.setTitle("Erro de validação");
        problemDetail.setType(URI.create("/errors/validacao"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setDetail("Um ou mais campos estão inválidos.");

        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors
                    .computeIfAbsent(fieldError.getField(), key -> new ArrayList<>())
                    .add(fieldError.getDefaultMessage());
        }

        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(TipoUsuarioNaoEncontradoException.class)
    public ProblemDetail handleTipoUsuarioNaoEncontradoException(
            TipoUsuarioNaoEncontradoException ex,
            HttpServletRequest request
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Tipo de usuário não encontrado");
        problemDetail.setType(URI.create("/errors/tipo-usuario-nao-encontrado"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail handleRecursoNaoEncontradoException(
            RecursoNaoEncontradoException ex,
            HttpServletRequest request
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setType(URI.create("/errors/recurso-nao-encontrado"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }
}