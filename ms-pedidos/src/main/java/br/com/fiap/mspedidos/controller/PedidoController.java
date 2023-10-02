package br.com.fiap.mspedidos.controller;

import br.com.fiap.mspedidos.dto.PedidoDTO;
import br.com.fiap.mspedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

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


}
