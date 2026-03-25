package br.com.fiap.unifiedeats2.compartilhado.infra.exception;

import br.com.fiap.unifiedeats2.compartilhado.core.exception.DadoDuplicadoException;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.RecursoNaoEncontradoException;
import br.com.fiap.unifiedeats2.compartilhado.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.tipousuario.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.unifiedeats2.usuario.core.exception.CredenciaisInvalidasException;
import br.com.fiap.unifiedeats2.usuario.core.exception.ParametroInvalidoException;
import br.com.fiap.unifiedeats2.usuario.core.exception.SenhaAtualInvalidaException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/v1/teste");
    }

    @Test
    void deveTratarUsuarioNaoEncontrado() {
        ProblemDetail problem = handler.handleUsuarioNaoEncontrado(new UsuarioNaoEncontradoException("Usuário não encontrado"), request);

        assertEquals(HttpStatus.NOT_FOUND.value(), problem.getStatus());
        assertEquals("Recurso não encontrado", problem.getTitle());
        assertEquals("Usuário não encontrado", problem.getDetail());
        assertEquals("/errors/usuario-nao-encontrado", problem.getType().toString());
        assertEquals("/v1/teste", problem.getInstance().toString());
        assertNotNull(problem.getProperties().get("timestamp"));
    }

    @Test
    void deveTratarErroGenerico() {
        ProblemDetail problem = handler.handleGeneric(new RuntimeException("boom"), request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), problem.getStatus());
        assertEquals("Erro interno", problem.getTitle());
        assertEquals("Ocorreu um erro inesperado", problem.getDetail());
        assertEquals("/errors/internal-server-error", problem.getType().toString());
        assertNotNull(problem.getProperties().get("timestamp"));
    }

    @Test
    void deveTratarSenhaAtualInvalida() {
        ProblemDetail problem = handler.handleSenhaAtualInvalidaException(new SenhaAtualInvalidaException("Senha atual inválida"), request);

        assertEquals(HttpStatus.UNAUTHORIZED.value(), problem.getStatus());
        assertEquals("Senha atual inválida", problem.getTitle());
        assertEquals("/errors/senha-atual-invalida", problem.getType().toString());
        assertNotNull(problem.getProperties().get("timestamp"));
    }

    @Test
    void deveTratarCredenciaisInvalidas() {
        ProblemDetail problem = handler.handleCredenciaisInvalidasException(new CredenciaisInvalidasException("Credenciais inválidas"), request);

        assertEquals(HttpStatus.UNAUTHORIZED.value(), problem.getStatus());
        assertEquals("Credenciais inválidas", problem.getTitle());
        assertEquals("/errors/credenciais-invalidas", problem.getType().toString());
        assertNotNull(problem.getProperties().get("timestamp"));
    }

    @Test
    void deveTratarDadoDuplicado() {
        ProblemDetail problem = handler.handleEmailDuplicadoException(new DadoDuplicadoException("Email duplicado"), request);

        assertEquals(HttpStatus.CONFLICT.value(), problem.getStatus());
        assertEquals("Conflito de dados", problem.getTitle());
        assertEquals("/errors/email-duplicado", problem.getType().toString());
        assertNotNull(problem.getProperties().get("timestamp"));
    }

    @Test
    void deveTratarParametroInvalido() {
        ProblemDetail problem = handler.handleInvalidCredentialsException(new ParametroInvalidoException("nome", "obrigatório"), request);

        assertEquals(HttpStatus.BAD_REQUEST.value(), problem.getStatus());
        assertEquals("Erro de validação", problem.getTitle());
        assertEquals("Parâmetro inválido", problem.getDetail());
        assertEquals("/errors/parametro-invalido", problem.getType().toString());
        assertEquals("obrigatório", ((java.util.Map<?, ?>) problem.getProperties().get("errors")).get("nome").toString().replace("[", "").replace("]", ""));
    }

    @Test
    void deveTratarMethodArgumentNotValidException() {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "objeto");
        bindingResult.addError(new FieldError("objeto", "email", "não deve estar em branco"));
        bindingResult.addError(new FieldError("objeto", "email", "formato inválido"));
        bindingResult.addError(new FieldError("objeto", "nome", "não deve estar em branco"));

        MethodArgumentNotValidException exception = Mockito.mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);

        ProblemDetail problem = handler.handleMethodArgumentNotValidException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST.value(), problem.getStatus());
        assertEquals("Erro de validação", problem.getTitle());
        assertEquals("Um ou mais campos estão inválidos.", problem.getDetail());
        assertEquals("/errors/validacao", problem.getType().toString());
        assertTrue(problem.getProperties().containsKey("errors"));
    }

    @Test
    void deveTratarTipoUsuarioNaoEncontrado() {
        ProblemDetail problem = handler.handleTipoUsuarioNaoEncontradoException(new TipoUsuarioNaoEncontradoException("Tipo não encontrado"), request);

        assertEquals(HttpStatus.NOT_FOUND.value(), problem.getStatus());
        assertEquals("Tipo de usuário não encontrado", problem.getTitle());
        assertEquals("/errors/tipo-usuario-nao-encontrado", problem.getType().toString());
    }

    @Test
    void deveTratarRecursoNaoEncontrado() {
        ProblemDetail problem = handler.handleRecursoNaoEncontradoException(new RecursoNaoEncontradoException("Recurso não encontrado"), request);

        assertEquals(HttpStatus.NOT_FOUND.value(), problem.getStatus());
        assertEquals("Recurso não encontrado", problem.getTitle());
        assertEquals("/errors/recurso-nao-encontrado", problem.getType().toString());
    }
}
