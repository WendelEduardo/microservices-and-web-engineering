package br.com.fiap.mspedidos.service;

import br.com.fiap.mspedidos.dto.PedidoDTO;
import br.com.fiap.mspedidos.model.Pedido;

import java.util.List;

public interface PedidoService {

    List<PedidoDTO> findAll();

    PedidoDTO findById(Long id);

    PedidoDTO insert(PedidoDTO pedidoDTO);

}
