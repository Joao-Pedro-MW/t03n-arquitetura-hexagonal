package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.repository;

import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.h2.PessoaRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PessoaEntity;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.jpa.PessoaJpaRepository;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.mapper.PessoaMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepositoryPortAdapter implements PessoaRepositoryPort {

    private final PessoaJpaRepository jpaRepository;

    public PessoaRepositoryPortAdapter(PessoaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PessoaBO salvar(PessoaBO bo) {
        PessoaEntity entity = PessoaMapper.toEntity(bo);
        PessoaEntity salva = jpaRepository.save(entity);
        return PessoaMapper.toBO(salva);
    }
}