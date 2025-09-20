package br.com.empresa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "marcas")
public class Marca extends PanacheEntity {
    public String descricao;
}
