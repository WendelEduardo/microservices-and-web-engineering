package br.com.fiap.mspagamentos.service;

import br.com.fiap.mspagamentos.repository.PagamentoRepository;
import br.com.fiap.mspagamentos.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest //carrega o contexto da aplicação
@Transactional //rollback no DB
public class PagamentoServiceIntegrationTest {

    @Autowired
    private PagamentoService service;
    @Autowired
    private PagamentoRepository repository;

    //preparando os dados
    private Long idExiste;
    private Long idNaoExiste;
    private Long totalRegistros;

    //... código omitido
    @BeforeEach
    void setup() throws Exception {
        idExiste = 1L;
        idNaoExiste = 50L;
        totalRegistros = 6L;
    }

    @Test
    @DisplayName("Deve deletar um pagamento pelo id")
    public void deleteDeveDeletarPagamentoQuandoIdExiste() {

        service.delete(idExiste);
        Assertions.assertEquals(totalRegistros - 1, repository.count());
    }
    //... código omitido

    // exercício

    @Test
    @DisplayName("Deve deletar um pagamento pelo id")
    public void deleteDeveLancarResourceNotFoundExceptionQuandoIdNaoExiste() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(idNaoExiste);
        });
    }

    //... código omitido
    @Test
    @DisplayName("Deve listar todos pagamentos e retornar status 200")
    public void findAllDeveListarTodosPagamentos(){

         var resultado =  service.findAll();
         Assertions.assertFalse(resultado.isEmpty());
         Assertions.assertEquals(totalRegistros, resultado.size());
         Assertions.assertEquals(Double.valueOf(1200.00), resultado.get(0).getValor().doubleValue() );
         Assertions.assertEquals("Amadeus", resultado.get(1).getNome());
         Assertions.assertEquals(null, resultado.get(5).getNome());
    }
    //... código omitido
}
