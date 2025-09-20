package br.com.empresa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria extends PanacheEntity {
    public String descricao;
}
