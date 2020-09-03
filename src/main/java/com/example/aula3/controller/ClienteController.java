package com.example.aula3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @GetMapping("/clientes")
    public String getClientes(){
        return "Um dia dia retornar todos os clientes aqui do banco de dados top";
    }

    @GetMapping("/clientes/{codigo}")
    public String getCliente(@PathVariable int codigo){
        if (codigo > 0)
            return "Cliente Id " + codigo;
        else
            return "Código Inválido";
    }
}