package com.example.aula3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.aula3.model.Cliente;

import org.springframework.stereotype.Component;

// Permite usar como componente em outras partes
@Component
public class ClienteRepository {
    
    private List <Cliente> clientes; 
    private int nextCode;

    @PostConstruct
    public void criarClientes(){
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        Cliente c3 = new Cliente();

        c1.setCodigo(1);
        c1.setNome("Amanda");
        c1.setEndereco("Rua X, 99");
        c1.setSaldo(100);

        c2.setCodigo(2);
        c2.setNome("Dridri");
        c2.setEndereco("Rua Y, 89");
        c2.setSaldo(550); 

        c3.setCodigo(3);
        c3.setNome("Mu");
        c3.setEndereco("Rua W, 79");
        c3.setSaldo(78);

        clientes = new ArrayList<Cliente>();
        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);
        nextCode=4;
    }

    public List<Cliente> getAllClientes(){
        return clientes;
    }
    
    public Optional<Cliente> getClienteByCodigo(int codigo){
        for (Cliente aux: clientes){
            if (aux.getCodigo() == codigo){
                return Optional.of(aux);
            }
        }       
        //return null;   -> não é bom retornar null
        return Optional.empty();
    }

    public Cliente save(Cliente cliente){
        cliente.setCodigo(nextCode++);
        clientes.add(cliente);
        return cliente;
    }

	public void remove(Cliente cliente) {
        clientes.remove(cliente);
	}

	public Cliente update(Cliente cliente) {
        Cliente aux = getClienteByCodigo(cliente.getCodigo()).get();
        
        if (aux!=null){
            aux.setEndereco(cliente.getEndereco());
            aux.setNome(cliente.getNome());
        }
		return aux;
	}
}

