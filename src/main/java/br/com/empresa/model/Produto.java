package br.com.empresa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto extends PanacheEntity {
    public String nome;
    public String descricao;
    @ManyToOne
    public Categoria categoria;
    @ManyToOne
    public Marca marca;
    public boolean ativo;
    public String imagem;
    public BigDecimal preco;
    @Column(name = "quantidade_estoque")
    public BigDecimal quantidadeEstoque;
}
