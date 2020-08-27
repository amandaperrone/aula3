package com.example.aula3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @GetMapping("/cliente")
    public String getClientes(){
        return "Vai um dia retornar todos os clientes aqui do banco de dados top";
    }

    @GetMapping("/cliente/{codigo}")
    public String getCliente(@PathVariable int codigo){
        if (codigo > 0)
            return "Cliente Id " + codigo;
        else
            return "Código Inválido";
    }
}