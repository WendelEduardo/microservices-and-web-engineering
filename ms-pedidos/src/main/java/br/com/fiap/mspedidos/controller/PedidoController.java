package br.com.fiap.mspedidos.controller;

import br.com.fiap.mspedidos.dto.PedidoDTO;
import br.com.fiap.mspedidos.dto.StatusDTO;
import br.com.fiap.mspedidos.service.PedidoService;
import br.com.fiap.mspedidos.service.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Path;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoServiceImpl service;

    @GetMapping
    public ResponseEntity< List<PedidoDTO>> findAll() {
        List<PedidoDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable @NotNull Long id){
        PedidoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> insert(@RequestBody @Valid PedidoDTO pedidoDTO, UriComponentsBuilder uriBuilder){
        PedidoDTO dto = service.insert(pedidoDTO);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> aprovarPagamanetoPedido(@PathVariable("id") Long id){
        service.aprovarPagamento(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> updateStatus(@PathVariable("id") Long id, @RequestBody StatusDTO statusDTO){
        PedidoDTO dto = service.updateStatus(id, statusDTO);
        return ResponseEntity.ok().body(dto);
    }



}
