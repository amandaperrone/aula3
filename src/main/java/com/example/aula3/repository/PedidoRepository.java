package com.example.aula3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.aula3.model.Pedido;

import org.springframework.stereotype.Component;

@Component
public class PedidoRepository {
    
    private ArrayList <Pedido> pedidos = new ArrayList<Pedido>(); 
    private static int nexNumero=1;

    public List<Pedido> getAllPedidos(){
        return pedidos;
    }

    public Optional<Pedido> getPedidoByNumero(long numero){
        for (Pedido aux: pedidos){
            if (aux.getNumero() == numero){
                return Optional.of(aux);
            }
        }       
        return Optional.empty();
    }

    public Pedido salvar(Pedido pedido){
        pedido.setNumero(nexNumero++);
        pedidos.add(pedido);
        return pedido;
    }
}
