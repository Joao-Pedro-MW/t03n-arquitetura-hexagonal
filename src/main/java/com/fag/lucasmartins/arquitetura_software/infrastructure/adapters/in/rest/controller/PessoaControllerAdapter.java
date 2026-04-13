package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.controller;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PessoaServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PessoaDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper.PessoaDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaControllerAdapter {

    private final PessoaServicePort pessoaServicePort;

    public PessoaControllerAdapter(PessoaServicePort pessoaServicePort) {
        this.pessoaServicePort = pessoaServicePort;
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> cadastrar(@RequestBody PessoaDTO dto) {
        PessoaBO bo = PessoaDTOMapper.toBO(dto);
        PessoaBO cadastrado = pessoaServicePort.cadastrar(bo);
        return ResponseEntity.status(201).body(PessoaDTOMapper.toDTO(cadastrado));
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listarTodos() {
        // Busca a lista de BOs através da porta de entrada
        List<PessoaBO> listaBO = pessoaServicePort.listarTodos();

        // Converte a lista de BO para DTO para enviar ao Bruno/Browser
        List<PessoaDTO> listaDTO = listaBO.stream()
                .map(PessoaDTOMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }
}