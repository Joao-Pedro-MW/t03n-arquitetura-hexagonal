package com.fag.lucasmartins.arquitetura_software.application.ports.in.service;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import java.util.List;

public interface PessoaServicePort {

    PessoaBO cadastrar(PessoaBO pessoaBO);

    List<PessoaBO> listarTodos(); // Adicione esta linha
}