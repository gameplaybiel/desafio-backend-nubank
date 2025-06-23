package com.desafio.nubank.mapper;

import com.desafio.nubank.dto.ClientesResponseDTO;
import com.desafio.nubank.dto.ContatoResponseDTO;
import com.desafio.nubank.model.Clientes;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static ClientesResponseDTO toDto(Clientes cliente){
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
