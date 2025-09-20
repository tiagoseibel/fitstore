package br.com.empresa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItensPedido extends PanacheEntity {
    @ManyToOne
    public Produto produto;
    @JsonBackReference
    @ManyToOne
    public Pedido pedido;
    public BigDecimal valor_unitario;
    public BigDecimal quantidade;
}
