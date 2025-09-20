package br.com.empresa.model;

import br.com.empresa.model.enumeration.StatusPedido;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class Pedido extends PanacheEntity {
    public UUID uuid;
    public LocalDate data_criacao;
    public LocalDate data_pedido;
    public LocalDate data_entrega;
    public BigDecimal valor_total;
    @ManyToOne
    public Usuario usuario;
    @Enumerated(EnumType.STRING)
    public StatusPedido status;
    @OneToMany(mappedBy = "pedido")
    public List<ItensPedido> itens;
}
