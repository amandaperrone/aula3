package com.example.aula3.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.aula3.dto.ClienteDTO;
import com.example.aula3.model.Cliente;
import com.example.aula3.repository.ClienteRepository;
import com.example.aula3.service.ClienteService;

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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

// O Spring por meio da anotação controla o objeto
@RestController
@RequestMapping("/clientes") //Indica que tudo é /clientes
public class ClienteController {

    // Spring irá injetar o objeto que gerencia
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteService service;

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
    // HttpServletRequest
    public ResponseEntity<Void> salvar( @RequestBody ClienteDTO clienteDTO, 
                                        HttpServletRequest request, 
                                        UriComponentsBuilder builder){

        Cliente cliente = service.fromDTO(clienteDTO);
        cliente = repository.save(cliente);
        // request.getRequestURI() pega o endereço que mandou
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + cliente.getCodigo())
        .build();

        return ResponseEntity.created(uriComponents.toUri()).build(); //.build() constrói a resposta
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
    public ResponseEntity<Cliente> atualizar(   @PathVariable int codigo, 
                                                @RequestBody ClienteDTO clienteDTO){
        if (repository.getClienteByCodigo(codigo) != null){
            Cliente cliente = service.fromDTO(clienteDTO); // transforma o cliente em clienteDTO
            cliente.setCodigo(codigo);
            cliente = repository.update(cliente);
            return ResponseEntity.ok(cliente);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}



