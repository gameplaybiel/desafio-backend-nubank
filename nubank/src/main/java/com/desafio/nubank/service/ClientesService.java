package com.desafio.nubank.service;

import com.desafio.nubank.dto.ClientesDTO;
import com.desafio.nubank.dto.ClientesResponseDTO;
import com.desafio.nubank.dto.ContatoResponseDTO;
import com.desafio.nubank.model.Clientes;
import com.desafio.nubank.model.Contato;
import com.desafio.nubank.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public Clientes salvarCliente(ClientesDTO dto){
        Clientes clientes = new Clientes();
        clientes.setNome(dto.getNome());

        if(dto.getContatos() != null && dto.getContatos().size() > 0){
            List<Contato> contatos = dto.getContatos().stream()
                    .map(c -> {
                       Contato contato = new Contato();
                       contato.setTelefone(c.getTelefone());
                       contato.setEmail(c.getEmail());
                       contato.setClientes(clientes);
                       return contato;
                    }).collect(Collectors.toList());
            clientes.setContatos(contatos);
        }
        return clientesRepository.save(clientes);
    }

    public List<ClientesResponseDTO> listarTodos(){
        return clientesRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<ContatoResponseDTO> listarContatosPorCliente(Long clienteId){
        Clientes cliente = clientesRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getContatos().stream().map(c -> {
            ContatoResponseDTO dto = new ContatoResponseDTO();
            dto.setId(c.getId());
            dto.setTelefone(c.getTelefone());
            dto.setEmail(c.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    private ClientesResponseDTO toDto(Clientes cliente){
        ClientesResponseDTO dto = new ClientesResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        List<ContatoResponseDTO> contatos = cliente.getContatos().stream()
                .map(c -> {
                    ContatoResponseDTO contatoDTO = new ContatoResponseDTO();
                    contatoDTO.setId(c.getId());
                    contatoDTO.setTelefone(c.getTelefone());
                    contatoDTO.setEmail(contatoDTO.getEmail());
                    return contatoDTO;
                }).collect(Collectors.toList());
        dto.setContatos(contatos);

        return dto;
    }
}
