package br.com.fiap.mspagamentos.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ms-pedidos")
public interface PedidosCliente {

    @RequestMapping(method = RequestMethod.PUT, value = "/pedidos/{id}/pago")
    Void aprovarPagamanetoPedido(@PathVariable("id") Long id);

}
