package com.example.aula3.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// O Spring por meio da anotação controla o objeto
@RestController
public class ClienteController {

    // Atributo, pois se fosse variável estaria dentro de um método
    List <Cliente> clientes; //= new ArrayList<Cliente>();

    // Fala pro spring chamar o método assim que inicializar 
    // Método criarClientes()
    @PostConstruct
    public void criarClientes(){
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        Cliente c3 = new Cliente();

        c1.codigo = 1;
        c1.nome = "Amanda";
        c1.endereco = "Rua X, 99";
        c1.saldo = 100;

        c2.codigo = 2;
        c2.nome = "Dridri";
        c2.endereco = "Rua Y, 89";
        c2.saldo = 550;

        c3.codigo = 3;
        c3.nome = "Mu";
        c3.endereco = "Rua W, 79";
        c3.saldo = 78;

        // Podem ser colocadas infinos objetos
        clientes = Arrays.asList(c1, c2, c3);
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return clientes;
    }

    @GetMapping("/clientes/{codigo}")
    public Cliente getCliente(@PathVariable int codigo){
        Cliente cli=null;

        // For simplificado
        /*
        for(int i=0; i < clientes.size(); i++){
            Cliente aux == clientes.get(i);
            if (aux.codigo == codigo){
                cli = aux;
                break;
            }
        }
        */

        // Esse for percorre todos os clientes da lista
        for (Cliente aux: clientes){
            // aux.codigo é atributo do objeto cliente e o outro é o parâmetro
            if (aux.codigo == codigo){
                cli = aux;
                break;
            }
        }       
        return cli;
    }
}