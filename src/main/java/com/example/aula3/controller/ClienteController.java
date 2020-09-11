package com.example.aula3.controller;

import java.util.List;

import com.example.aula3.model.Cliente;
import com.example.aula3.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/clientes")
    // @RequestBody pega os dados que o postman enviar e guarda
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }
}



