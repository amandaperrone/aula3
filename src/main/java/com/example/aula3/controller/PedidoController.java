package com.example.aula3.controller;

import java.util.List;

import com.example.aula3.model.Pedido;
import com.example.aula3.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService service;

    //------------------- ENDPOINTS -------------------
    @GetMapping
    public List<Pedido> getAllPedidos(){
        return service.getAllPedidos();
    }
    
    @GetMapping("/{numero}")
    public ResponseEntity<Pedido> getPedidoByNumero(@PathVariable long numero){
        Pedido pedido = service.getPedidoByNumero(numero);
        return ResponseEntity.ok(pedido);
    }

    


}
