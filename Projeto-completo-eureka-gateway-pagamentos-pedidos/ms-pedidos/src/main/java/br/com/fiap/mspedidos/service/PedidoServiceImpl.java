package br.com.fiap.mspedidos.service;

import br.com.fiap.mspedidos.dto.PedidoDTO;
import br.com.fiap.mspedidos.dto.StatusDTO;
import br.com.fiap.mspedidos.model.ItemDoPedido;
import br.com.fiap.mspedidos.model.Pedido;
import br.com.fiap.mspedidos.model.Status;
import br.com.fiap.mspedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository repository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll() {
        List<Pedido> list = repository.findAll();

        return list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        Pedido pedido = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado")
        ) ;

        return new PedidoDTO(pedido);

    }

    @Transactional
    public PedidoDTO insert(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        copyDtoToEntity(pedidoDTO, pedido);
        pedido = repository.save(pedido);

        return new PedidoDTO(pedido);
    }

    @Transactional
    public void aprovarPagamento(Long id){
        Pedido pedido = repository.getByIdWithItems(id);

        if(pedido == null){
            throw new EntityNotFoundException("Recurso não encontrado.");
        }

        pedido.setStatus(Status.PAGO);
        repository.updateStatus(Status.PAGO, pedido);
    }


    @Transactional
    public PedidoDTO updateStatus(Long id, StatusDTO status){
        Pedido pedido = repository.getByIdWithItems(id);

        if(pedido == null){
            throw new EntityNotFoundException("Recurso não encontrado.");
        }

        pedido.setStatus(status.getStatus());
        repository.updateStatus(status.getStatus(), pedido);
        return new PedidoDTO(pedido);
    }


    private Pedido copyDtoToEntity(PedidoDTO dto, Pedido pedido){
        pedido.setId(dto.getId());
        pedido.setStatus(Status.CONFIRMADO);
        pedido.setDataHora(LocalDateTime.now());

        List<ItemDoPedido> itens = new ArrayList<>();

        dto.getItens().forEach(item -> {
            ItemDoPedido itemDoPedido = new ItemDoPedido(item.getId(), item.getQuantidade(), item.getDescricao());
            itemDoPedido.setPedido(pedido);
            itens.add(itemDoPedido);
        });

        pedido.setItens(itens);

        return pedido;
    }


}
