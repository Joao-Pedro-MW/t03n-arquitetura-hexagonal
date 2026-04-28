package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.listener;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.dto.SqsOrderEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.mapper.SqsOrderEventMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class EntradaPedidoSqsAdapter {

    private final PedidoServicePort pedidoServicePort;
    private final SqsOrderEventMapper mapper;

    public EntradaPedidoSqsAdapter(PedidoServicePort pedidoServicePort, SqsOrderEventMapper mapper) {
        this.pedidoServicePort = pedidoServicePort;
        this.mapper = mapper;
    }

    @SqsListener(value = "${queue.order-events}")
    public void listen(SqsOrderEventDTO evento) {
        System.out.println("Mensagem recebida da fila. customerId: " + evento.getCustomerId());

        PedidoBO pedidoBO = mapper.toBO(evento);
        PedidoBO pedidoCriado = pedidoServicePort.criarPedido(pedidoBO);

        System.out.println("Pedido criado com sucesso. id: " + pedidoCriado.getId());
    }
}