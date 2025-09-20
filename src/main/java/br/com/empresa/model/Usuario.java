package br.com.empresa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario extends PanacheEntity {

    @Column(name = "nome_completo", length = 300)
    public String nomeCompleto;

    @Column(length = 500)
    public String endereco;

    @Column(length = 100)
    public String cidade;

    @Column(length = 2)
    public String uf;
    public boolean ativo;

    @Column(name = "data_cadastro")
    public LocalDate dataCadastro;

    @Column(length = 8)
    public String cep;

    @Column(length = 150)
    public String email;

    public String senha;
}
