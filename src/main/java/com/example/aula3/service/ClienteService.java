package com.example.aula3.service;

import java.util.List;
import java.util.Optional;

import com.example.aula3.dto.ClienteDTO;
import com.example.aula3.model.Cliente;
import com.example.aula3.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Serviços = regras de negócio
@Service // Como é uma classe de serviço, informa que esse cliente é gerenciado pelo spring boot, como tipo de serviço
public class ClienteService {

    // inserir o repositório
    @Autowired
    private ClienteRepository repositorio;
    
    public Cliente fromDTO(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setEndereco(dto.getEndereco());
        cliente.setNome(dto.getNome());

        return cliente;
    }

	public List<Cliente> getAllClientes() {
		return repositorio.getAllClientes();
	}

	public Cliente getClienteByCodigo(int codigo) { // necessário adicionar o 404
        Optional<Cliente> op = repositorio.getClienteByCodigo(codigo);
        // Se o Optional tiver um cliente, retona ele. Se não, o Throw manda uma excessão
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não cadastrado")); 
	}

	public Cliente save(Cliente cliente) {
		return repositorio.save(cliente);
	}

	public void removeByCodigo(int codigo) {
        repositorio.remove(getClienteByCodigo(codigo));
	}

	public Cliente update(Cliente cliente) {
        getClienteByCodigo(cliente.getCodigo()); // Verifica se há um cliente, se não, vem 404
        return repositorio.update(cliente);
	}
}
