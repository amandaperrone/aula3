package com.example.aula3.controller;

import java.net.URI;
import java.util.List;

import com.example.aula3.model.Cliente;
import com.example.aula3.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// O Spring por meio da anotação controla o objeto
@RestController
@RequestMapping("/clientes") //Indica que tudo é /clientes
public class ClienteController {

    // Spring irá injetar o objeto que gerencia
    @Autowired
    private ClienteRepository repository;

    @GetMapping()
    public List<Cliente> getClientes(){
        return repository.getAllClientes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> getCliente(@PathVariable int codigo){
        Cliente cliente = repository.getClienteByCodigo(codigo);

        if (cliente!=null){
            return ResponseEntity.ok(cliente); // Só o 200 não precisa de build()
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    // @RequestBody pega os dados que o postman enviar e guarda
    public ResponseEntity<Void> salvar(@RequestBody Cliente cliente){
        cliente = repository.save(cliente);

        URI uri = URI.create("http://localhost:8080/clientes/" + cliente.getCodigo());

        return ResponseEntity.created(uri).build(); //.build() constrói a resposta
    }

    @DeleteMapping("/{codigo}")
    // O corpo é void pois não retorna nada
    public ResponseEntity<Void> remover(@PathVariable int codigo){
        Cliente cliente = repository.getClienteByCodigo(codigo);

        if (cliente!=null){
            repository.remove(cliente); //ctrl em cima do remove e criar método
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    // O corpo é void pois não retorna nada
    public ResponseEntity<Cliente> atualizar(@PathVariable int codigo, @RequestBody Cliente cliente){
        if (repository.getClienteByCodigo(codigo) != null){
            cliente.setCodigo(codigo);
            cliente = repository.update(cliente);
            return ResponseEntity.ok(cliente);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}



