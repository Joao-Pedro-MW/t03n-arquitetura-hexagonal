package com.fag.lucasmartins.arquitetura_software.application.services;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PessoaServicePort;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.h2.PessoaRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService implements PessoaServicePort {

    private final PessoaRepositoryPort pessoaRepositoryPort;

    public PessoaService(PessoaRepositoryPort pessoaRepositoryPort) {
        this.pessoaRepositoryPort = pessoaRepositoryPort;
    }

    @Override
    public PessoaBO cadastrar(PessoaBO pessoaBO) {
        pessoaBO.validar(); // Validação no Modelo Rico
        return pessoaRepositoryPort.salvar(pessoaBO);
    }

    @Override
    public List<PessoaBO> listarTodos() {
        return List.of();
    }
}