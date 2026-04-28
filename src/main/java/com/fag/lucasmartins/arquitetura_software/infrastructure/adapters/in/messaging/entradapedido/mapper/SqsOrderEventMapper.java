package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.dto.SqsOrderEventDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SqsOrderEventMapper {

    public PedidoBO toBO(SqsOrderEventDTO dto) {
        PedidoBO pedidoBO = new PedidoBO();

        // zipCode (SQS) → cep (domínio)
        pedidoBO.setCep(dto.getZipCode());

        // customerId (SQS) → pessoa.id (domínio)
        // O service buscará a Pessoa completa pelo id; aqui apenas referenciamos
        PessoaBO pessoaBO = new PessoaBO();
        pessoaBO.setId(dto.getCustomerId());
        pedidoBO.setPessoa(pessoaBO);

        // orderItems (SQS) → itens (domínio)
        List<PedidoProdutoBO> itens = dto.getOrderItems().stream()
                .map(this::toItemBO)
                .collect(Collectors.toList());
        pedidoBO.setItens(itens);

        return pedidoBO;
    }

    private PedidoProdutoBO toItemBO(SqsOrderEventDTO.SqsOrderItemDTO itemDTO) {
        PedidoProdutoBO itemBO = new PedidoProdutoBO();

        // sku (SQS) → produto.id (domínio)
        ProdutoBO produtoBO = new ProdutoBO();
        produtoBO.setId(itemDTO.getSku());
        itemBO.setProduto(produtoBO);

        // amount (SQS) → quantidade (domínio)
        itemBO.setQuantidade(itemDTO.getAmount());

        return itemBO;
    }
}