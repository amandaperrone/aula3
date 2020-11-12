package com.example.aula3.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.aula3.dto.PedidoDTO;
import com.example.aula3.model.Cliente;
import com.example.aula3.model.Pedido;
import com.example.aula3.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repositorio;

    @Autowired
    private ClienteService clienteService;

    public PedidoDTO toDTO(Pedido pedido){
        PedidoDTO dto = new PedidoDTO();
        dto.setDataPedido(pedido.getDataPedido());
        dto.setDescricao(pedido.getDescricao());
        dto.setItens(pedido.getItens());
        dto.setNumero(pedido.getNumero());
        dto.setPedidoFechado(pedido.isPedidoFechado());
        dto.setTotalPedido(pedido.totalPedido());
        return dto;
    }

    public List<PedidoDTO> toListDTO(List<Pedido> pedidos){
        ArrayList<PedidoDTO> dtoList = new ArrayList<PedidoDTO>();
        for (Pedido pedido : pedidos){
            dtoList.add(toDTO(pedido));
        }
        return dtoList;
    }


    public List<Pedido> getAllPedidos(){
        return repositorio.getAllPedidos();
    }

    public Pedido getPedidoByNumero(long numero) { 
        Optional<Pedido> op = repositorio.getPedidoByNumero(numero);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não cadastrado")); 
    }

    public Pedido salvar(Pedido pedido, int codigo){
        // Verificando se existe um cliente com o código informado
        // Se não, manda o 404 e não executa as demais linhas
        Cliente cliente = clienteService.getClienteByCodigo((codigo));
        // Se executar as próximas linhas, existe o cliente informado
        pedido.setDataPedido(LocalDateTime.now());
        // Associar pedido>cliente e cleinte>pedido
        pedido.setCliente(cliente);
        cliente.addPedido(pedido);
        return repositorio.salvar(pedido);
    }

}