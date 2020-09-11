package com.example.aula5.controller;

import java.util.List;

import com.example.aula5.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import repository.ClienteRepository;

// O Spring por meio da anotação controla o objeto
@RestController
public class ClienteController {

    // Spring irá injetar o objeto que gerencia
    @Autowired
    private ClienteRepository repository;

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return repository.getAllClientes();
    }

    @GetMapping("/clientes/{codigo}")
    public Cliente getCliente(@PathVariable int codigo){
        return repository.getClienteByCodigo(codigo);
    }
}