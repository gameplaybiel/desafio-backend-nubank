package com.desafio.nubank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientesResponseDTO {
    private Long id;
    private String nome;
    private List<ContatoResponseDTO> contatos;
}
