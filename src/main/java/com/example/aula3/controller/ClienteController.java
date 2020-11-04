package com.example.aula3.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.aula3.dto.ClienteDTO;
import com.example.aula3.model.Cliente;
import com.example.aula3.model.Pedido;
import com.example.aula3.service.ClienteService;
import com.example.aula3.service.PedidoService;

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

    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping()
    public List<Cliente> getClientes(){
        return clienteService.getAllClientes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> getCliente(@PathVariable int codigo){
        
        Cliente cliente = clienteService.getClienteByCodigo(codigo);
        return ResponseEntity.ok(cliente); // Só o 200 não precisa de build()
    }

    @PostMapping()
    // @RequestBody pega os dados que o postman enviar e guarda
    // HttpServletRequest
    public ResponseEntity<Void> salvar(@RequestBody ClienteDTO clienteDTO, HttpServletRequest request,UriComponentsBuilder builder){

        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente = clienteService.save(cliente);
        // request.getRequestURI() pega o endereço que mandou
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + cliente.getCodigo()).build();

        return ResponseEntity.created(uriComponents.toUri()).build(); //.build() constrói a resposta
    }

    @DeleteMapping("/{codigo}")
    // O corpo é void pois não retorna nada
    public ResponseEntity<Void> remover(@PathVariable int codigo){
        
        clienteService.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{codigo}")
    // O corpo é void pois não retorna nada
    public ResponseEntity<Cliente> atualizar(@PathVariable int codigo, @RequestBody ClienteDTO clienteDTO){

        Cliente cliente = clienteService.fromDTO(clienteDTO); // transforma o cliente em clienteDTO
        cliente.setCodigo(codigo);
        cliente = clienteService.update(cliente);
        return ResponseEntity.ok(cliente);
       
    }

    @PostMapping("{id}/pedidos")
    public ResponseEntity<Void> salvar(@PathVariable int id, @RequestBody Pedido pedido, HttpServletRequest request,UriComponentsBuilder builder){
        pedido = pedidoService.salvar(pedido, id);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + pedido.getNumero()).build();

        return ResponseEntity.created(uriComponents.toUri()).build(); 
    }
}



