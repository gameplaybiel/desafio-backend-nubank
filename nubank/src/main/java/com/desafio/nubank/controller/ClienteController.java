package com.desafio.nubank.controller;

import com.desafio.nubank.dto.ClientesDTO;
import com.desafio.nubank.dto.ClientesResponseDTO;
import com.desafio.nubank.dto.ContatoResponseDTO;
import com.desafio.nubank.model.Clientes;
import com.desafio.nubank.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<Clientes> criar(@RequestBody ClientesDTO dto){
        Clientes clienteSalvo = clientesService.salvarCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDTO>> listarTodos(){
        return ResponseEntity.ok(clientesService.listarTodos());
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatoResponseDTO>> listarContatos(@PathVariable Long id){
        return ResponseEntity.ok(clientesService.listarContatosPorCliente(id));
    }
}
