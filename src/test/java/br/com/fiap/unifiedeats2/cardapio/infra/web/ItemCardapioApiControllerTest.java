package br.com.fiap.unifiedeats2.cardapio.infra.web;

import br.com.fiap.unifiedeats2.cardapio.core.controller.*;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioInputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.CadastrarItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.core.dto.ItemCardapioOutputDTO;
import br.com.fiap.unifiedeats2.cardapio.infra.web.json.AtualizarItemCardapioRequestJson;
import br.com.fiap.unifiedeats2.cardapio.infra.web.json.CadastrarItemCardapioRequestJson;
import br.com.fiap.unifiedeats2.compartilhado.core.dto.EnderecoOutputDTO;
import br.com.fiap.unifiedeats2.restaurante.core.dto.RestauranteOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemCardapioApiControllerTest {

    private CadastrarItemCardapioController cadastrarItemCardapioController;
    private BuscarItemCardapioPorIdController buscarItemCardapioPorIdController;
    private ListarItensCardapioController listarItensCardapioController;
    private AtualizarItemCardapioController atualizarItemCardapioController;
    private RemoverItemCardapioController removerItemCardapioController;

    private ItemCardapioApiController controller;

    @BeforeEach
    void setUp() {
        cadastrarItemCardapioController = mock(CadastrarItemCardapioController.class);
        buscarItemCardapioPorIdController = mock(BuscarItemCardapioPorIdController.class);
        listarItensCardapioController = mock(ListarItensCardapioController.class);
        atualizarItemCardapioController = mock(AtualizarItemCardapioController.class);
        removerItemCardapioController = mock(RemoverItemCardapioController.class);

        controller = new ItemCardapioApiController(
                cadastrarItemCardapioController,
                buscarItemCardapioPorIdController,
                listarItensCardapioController,
                atualizarItemCardapioController,
                removerItemCardapioController
        );
    }

    @Test
    void deveCadastrarItemCardapio() {
        when(cadastrarItemCardapioController.cadastrar(any(CadastrarItemCardapioInputDTO.class)))
                .thenReturn(new CadastrarItemCardapioOutputDTO(
                        10L,
                        "Prato A",
                        "Descricao A",
                        BigDecimal.valueOf(39.90),
                        true,
                        "foto.jpg",
                        1L
                ));

        CadastrarItemCardapioRequestJson json = new CadastrarItemCardapioRequestJson(
                "Prato A",
                "Descricao A",
                BigDecimal.valueOf(39.90),
                true,
                "foto.jpg",
                1L
        );

        ResponseEntity<Void> response = controller.cadastrar(json);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("/v1/itens-cardapio/10", response.getHeaders().getLocation().toString());

        ArgumentCaptor<CadastrarItemCardapioInputDTO> captor =
                ArgumentCaptor.forClass(CadastrarItemCardapioInputDTO.class);

        verify(cadastrarItemCardapioController).cadastrar(captor.capture());

        var input = captor.getValue();
        assertEquals("Prato A", input.nome());
        assertEquals("Descricao A", input.descricao());
        assertEquals(BigDecimal.valueOf(39.90), input.preco());
        assertTrue(input.disponivelApenasNoLocal());
        assertEquals("foto.jpg", input.foto());
        assertEquals(1L, input.restauranteId());
    }

    @Test
    void deveListarItensCardapio() {
        List<ItemCardapioOutputDTO> output = List.of(itemOutput());
        when(listarItensCardapioController.listar()).thenReturn(output);

        ResponseEntity<List<ItemCardapioOutputDTO>> response = controller.listar();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }

    @Test
    void deveBuscarItemPorId() {
        ItemCardapioOutputDTO output = itemOutput();
        when(buscarItemCardapioPorIdController.buscarPorId(1L)).thenReturn(output);

        ResponseEntity<ItemCardapioOutputDTO> response = controller.buscarPorId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(output, response.getBody());
    }

    @Test
    void deveAtualizarItemCardapio() {
        AtualizarItemCardapioRequestJson json = new AtualizarItemCardapioRequestJson(
                "Prato Atualizado",
                "Nova descricao",
                BigDecimal.valueOf(49.90),
                false,
                "nova-foto.jpg",
                2L
        );

        ResponseEntity<Void> response = controller.atualizar(5L, json);

        assertEquals(204, response.getStatusCode().value());
        verify(atualizarItemCardapioController).atualizar(any());
    }

    @Test
    void deveRemoverItemCardapio() {
        ResponseEntity<Void> response = controller.remover(8L);

        assertEquals(204, response.getStatusCode().value());
        verify(removerItemCardapioController).remover(8L);
    }

    private ItemCardapioOutputDTO itemOutput() {
        return new ItemCardapioOutputDTO(
                1L,
                "Prato A",
                "Descricao A",
                BigDecimal.valueOf(39.90),
                true,
                "foto.jpg",
                new RestauranteOutputDTO(
                        1L,
                        "Restaurante A",
                        new EnderecoOutputDTO("03450000", "Rua A", "10", "Apto 1", "Centro", "São Paulo", "SP"),
                        "Brasileira",
                        "09:00 às 18:00",
                        1L
                )
        );
    }
}