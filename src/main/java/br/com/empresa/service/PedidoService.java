package br.com.empresa.service;

import br.com.empresa.model.ItensPedido;
import br.com.empresa.model.Pedido;
import br.com.empresa.model.Produto;
import br.com.empresa.model.Usuario;
import br.com.empresa.model.enumeration.StatusPedido;
import br.com.empresa.payload.PedidoPayload;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PedidoService {

    @Transactional
    public void adicionarItem(PedidoPayload pedidoPayload) {
        UUID uuid;
        Pedido pedido;
        if (pedidoPayload.uuid() != null) {
            pedido = Pedido.find("uuid = ?1", pedidoPayload.uuid()).firstResult();
        } else {
            pedido = new Pedido();
            pedido.uuid = UUID.randomUUID();
            pedido.data_criacao = LocalDate.now();
            pedido.data_pedido = LocalDate.now();
            pedido.data_entrega = LocalDate.now();
            pedido.valor_total = new BigDecimal(0);
            pedido.status = StatusPedido.ABERTO;
        }
        ItensPedido itens = new ItensPedido();
        itens.pedido = pedido;
        itens.produto = Produto.findById(pedidoPayload.idProduto());
        itens.quantidade = pedidoPayload.quantidade();
        itens.valor_unitario = new BigDecimal(0);

        pedido.persist();
        itens.persist();
    }

    @Transactional
    public void confirmarPedido(UUID uuid, Usuario usuario) {
        Pedido pedido = Pedido.find("uuid = ?1", uuid).firstResult();
        pedido.usuario = usuario;
        pedido.status = StatusPedido.CONFIRMADO;
        pedido.persist();
    }

    public Optional<Pedido> pedidoAberto(UUID uuid) {
        return Pedido.find("uuid = ?1", uuid).firstResultOptional();
    }
}
