package br.com.fiap.unifiedeats2.restaurante.infra.web;

import br.com.fiap.unifiedeats2.compartilhado.core.dto.EnderecoOutputDTO;
import br.com.fiap.unifiedeats2.compartilhado.infra.web.json.EnderecoRequestJson;
import br.com.fiap.unifiedeats2.restaurante.core.controller.*;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteInputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.CadastrarRestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.infra.web.json.AtualizarRestauranteRequestJson;
import br.com.fiap.unifiedeats2.restaurante.infra.web.json.CadastrarRestauranteRequestJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestauranteApiControllerTest {

    private CadastrarRestauranteController cadastrarRestauranteController;
    private BuscarRestaurantePorIdController buscarRestaurantePorIdController;
    private ListarRestaurantesController listarRestaurantesController;
    private AtualizarRestauranteController atualizarRestauranteController;
    private RemoverRestauranteController removerRestauranteController;

    private RestauranteApiController controller;

    @BeforeEach
    void setUp() {
        cadastrarRestauranteController = mock(CadastrarRestauranteController.class);
        buscarRestaurantePorIdController = mock(BuscarRestaurantePorIdController.class);
        listarRestaurantesController = mock(ListarRestaurantesController.class);
        atualizarRestauranteController = mock(AtualizarRestauranteController.class);
        removerRestauranteController = mock(RemoverRestauranteController.class);

        controller = new RestauranteApiController(
                cadastrarRestauranteController,
                buscarRestaurantePorIdController,
                listarRestaurantesController,
                atualizarRestauranteController,
                removerRestauranteController
        );
    }

    @Test
    void deveCadastrarRestaurante() {
        when(cadastrarRestauranteController.cadastrar(any(CadastrarRestauranteInputDTO.class)))
                .thenReturn(new CadastrarRestauranteOutputDTO(10L));

        CadastrarRestauranteRequestJson json = new CadastrarRestauranteRequestJson(
                "Restaurante A",
                enderecoRequest(),
                "Brasileira",
                "09:00 às 18:00",
                1L
        );

        ResponseEntity<Void> response = controller.cadastrar(json);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("/v1/restaurantes/10", response.getHeaders().getLocation().toString());

        ArgumentCaptor<CadastrarRestauranteInputDTO> captor =
                ArgumentCaptor.forClass(CadastrarRestauranteInputDTO.class);

        verify(cadastrarRestauranteController).cadastrar(captor.capture());

        var input = captor.getValue();
        assertEquals("Restaurante A", input.nome());
        assertEquals("03450000", input.endereco().cep());
        assertEquals("Brasileira", input.tipoCozinha());
        assertEquals("09:00 às 18:00", input.horarioFuncionamento());
        assertEquals(1L, input.donoId());
    }

    @Test
    void deveListarRestaurantes() {
        List<RestauranteOutputDTO> output = List.of(restauranteOutput());
        when(listarRestaurantesController.listar()).thenReturn(output);

        ResponseEntity<List<RestauranteOutputDTO>> response = controller.listar();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }

    @Test
    void deveBuscarRestaurantePorId() {
        RestauranteOutputDTO output = restauranteOutput();
        when(buscarRestaurantePorIdController.buscarPorId(1L)).thenReturn(output);

        ResponseEntity<RestauranteOutputDTO> response = controller.buscarPorId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }

    @Test
    void deveAtualizarRestaurante() {
        AtualizarRestauranteRequestJson json = new AtualizarRestauranteRequestJson(
                "Restaurante Atualizado",
                enderecoRequest(),
                "Italiana",
                "10:00 às 22:00",
                2L
        );

        ResponseEntity<Void> response = controller.atualizar(5L, json);

        assertEquals(204, response.getStatusCode().value());
        verify(atualizarRestauranteController).atualizar(any());
    }

    @Test
    void deveRemoverRestaurante() {
        ResponseEntity<Void> response = controller.remover(8L);

        assertEquals(204, response.getStatusCode().value());
        verify(removerRestauranteController).remover(8L);
    }

    private RestauranteOutputDTO restauranteOutput() {
        return new RestauranteOutputDTO(
                1L,
                "Restaurante A",
                new EnderecoOutputDTO("03450000", "Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP"),
                "Brasileira",
                "09:00 às 18:00",
                1L
        );
    }

    private EnderecoRequestJson enderecoRequest() {
        return new EnderecoRequestJson("03450000", "Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP");
    }
}