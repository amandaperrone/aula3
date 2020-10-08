package com.example.aula3.service;

import com.example.aula3.dto.ClienteDTO;
import com.example.aula3.model.Cliente;

import org.springframework.stereotype.Service;

//Serviços = regras de negócio
@Service // Como é uma classe de serviço, informa que esse cliente é gerenciado pelo spring boot, como tipo de serviço
public class ClienteService {
    public Cliente fromDTO(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setEndereco(dto.getEndereco());
        cliente.setNome(dto.getNome());

        return cliente;
    }
}
