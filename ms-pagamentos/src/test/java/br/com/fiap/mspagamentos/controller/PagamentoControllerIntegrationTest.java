package br.com.fiap.mspagamentos.controller;

import br.com.fiap.mspagamentos.dto.PagamentoDTO;
import br.com.fiap.mspagamentos.model.Pagamento;
import br.com.fiap.mspagamentos.model.Status;
import br.com.fiap.mspagamentos.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional //rollback no DB
public class PagamentoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    //preparando os dados
    private Long idExiste;
    private Long idNaoExiste;
    private Long totalRegistros;
    private PagamentoDTO pagamentoDTO;

    //... código omitido

    // converter para JSON o objeto java e enviar na requisição
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {
        idExiste = 1L;
        idNaoExiste = 50L;
        totalRegistros = 6L;
        //criando um PagamentoDTO
        pagamentoDTO = Factory.createPagamentoDTO();
    }

    //... código omitido

    @Test
    @DisplayName("Deve listar todos os pagamentos e retornar status 200")
    public void findAllShouldListAllPagamentos() throws Exception {
        // chamando uma requisição com o método get no caminho /pagamentos
        mockMvc.perform(get("/pagamentos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").isString())
                .andExpect(jsonPath("$[0].nome").value("Nicodemus"))
                .andExpect(jsonPath("$[5].status").value("CRIADO"));
    }
    //... código omitido

    @Test
    @DisplayName("Deve retornar um pagamento pelo ID e com status 200")
    public void findByIdShouldFindPagamentoById() throws Exception {
        // chamando uma requisição com o método get no caminho /pagamentos
        mockMvc.perform(get("/pagamentos/{id}", idExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Nicodemus"));
    }

    @Test
    @DisplayName("findById Deve Retornar Not Found Quando Id Não Existe")
    public void findByIdDeveRetornarNotFoundQuandoIdNaoExiste() throws Exception {
        //NotFound - código 404
        mockMvc.perform(get("/pagamentos/{id}", idNaoExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    // ... código omitido
    @Test
    @DisplayName("Deve salvar um pagamento, retornar status 201 e Location no Header")
    public void insertDeveSalvarPagamento() throws Exception {
        Pagamento entity = Factory.createPagamento();
        entity.setId(null);

        String corpoJson = objectMapper.writeValueAsString(entity);

        mockMvc.perform(post("/pagamentos")
                        .content(corpoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.valor").exists())
                .andExpect(jsonPath("$.nome").value("Bach"));
    }
    // ... código omitido


    @Test
    @DisplayName("Deve salvar um pagamento com os campos obrigatórios, " +
            "retornar status 201 e Location no Header")
    public void insertDeveSalvarPagamentoCamposObrigatorios() throws Exception {
        Pagamento entity = new Pagamento(null, BigDecimal.valueOf(25.25), null, null, null, null, Status.CRIADO, 7L, 1L);

        String corpoJson = objectMapper.writeValueAsString(entity);
        mockMvc.perform(post("/pagamentos")
                        .content(corpoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.valor").exists())
                .andExpect(jsonPath("$.nome").isEmpty());
    }

    @Test
    @DisplayName("Insert deve lançar exception quando dados inválidos e retornar status 422")
    public void insertDeveLancarExceptionQuandoDadosInvalidos() throws Exception {
        Pagamento entity = new Pagamento();
        entity.setValor(BigDecimal.valueOf(25.25));
        entity.setFormaDePagamentoId(1L);

        String corpoJson = objectMapper.writeValueAsString(entity);
        mockMvc.perform(post("/pagamentos")
                        .content(corpoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("update Deve atualizar Pagamento Quando Id Existe e retornar status 200")
    public void updateDeveAtualizarPagamentoQuandoIdExiste() throws Exception {

        String corpoJson = objectMapper.writeValueAsString(pagamentoDTO);
        mockMvc.perform(put("/pagamentos/{id}", idExiste)
                        .content(corpoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.valor").exists())
                .andExpect(jsonPath("$.status").exists());

    }

    @Test
    @DisplayName("update Deve Retornar NotFound Quando Id Não Existe")
    public void updateDeveRetornarNotFoundQuandoIdNaoExiste() throws Exception {
        String corpoJson = objectMapper.writeValueAsString(pagamentoDTO);
        mockMvc.perform(put("/pagamentos/{id}", idNaoExiste)
                        .content(corpoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("delete Deve Retornar NoContent Quando Id Existe, status 204")
    public void deleteDeveRetornarNoContentQuandoIdExiste() throws Exception {

        mockMvc.perform(delete("/pagamentos/{id}", idExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("delete Deve Retornar NotFound Quando Id Não Existe, status 404")
    public void deleteDeveRetornarNotFoundQuandoIdNaoExiste() throws Exception {

        mockMvc.perform(delete("/pagamentos/{id}", idNaoExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("Deve alterar status para CONFIRMADO quando Id Existe, status 200")
    public void patchDeveAlterarStatusParaConfirmadoQuandoIdExiste() throws Exception {

        mockMvc.perform(patch("/pagamentos/{id}/confirmar", idExiste)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Deve alterar status para CANCELADO quando Id Existe, status 200")
    public void patchDeveAlterarStatusParaCanceladoQuandoIdExiste() throws Exception {

        mockMvc.perform(patch("/pagamentos/{id}/cancelar", idExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Deve retornar status 404 quando Id Não Existe")
    public void patchDeveRetornarNotFound404QuandoIdNaoExiste() throws Exception {

        mockMvc.perform(patch("/pagamentos/{id}/confirmar", idNaoExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("Deve retornar status 404 quando Id Não Existe")
    public void patchDeveRetornarNotFoundQuandoIdNaoExiste() throws Exception {

        mockMvc.perform(patch("/pagamentos/{id}/cancelar", idNaoExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
