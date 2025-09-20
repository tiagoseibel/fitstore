package br.com.empresa.service;

import br.com.empresa.model.Produto;
import br.com.empresa.payload.Page;
import br.com.empresa.payload.PageRequest;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PathParam;

import java.io.InputStream;
import java.util.List;

@ApplicationScoped
public class ProdutoService {

    public List<Produto> listarTodos() {
        return Produto.listAll();
    }

    public Produto findById(Long id) {
        return Produto.findById(id);
    }

    public Page findByCategoria(Long idCategoria, PageRequest pageRequest) {
        PanacheQuery<Produto> query = Produto.find("categoria.id = ?1", idCategoria);
        Long totalElements = query.count();
        Long totalPages = totalElements / pageRequest.size();
        List<Produto> produtos = query.page(pageRequest.page(), pageRequest.size()).list();
        return new Page(produtos, pageRequest, totalElements, totalPages);
    }

    public Page findByMarca(Long idMarca, PageRequest pageRequest) {
        PanacheQuery<Produto> query = Produto.find("marca.id = ?1", idMarca);
        Long totalElements = query.count();
        Long totalPages = totalElements / pageRequest.size();
        List<Produto> produtos = query.page(pageRequest.page(), pageRequest.size()).list();
        return new Page(produtos, pageRequest, totalElements, totalPages);
    }

    public InputStream buscarFotoProduto(@PathParam("id") Long id) {
        return getClass().getResourceAsStream("/META-INF/resources/images/tenis-fila.jpeg");
    }

    @Transactional
    public Produto save(Produto produto) {
        Produto.persist(produto);
        return produto;
    }

    @Transactional
    public void delete(Long id) {
        Produto produto = Produto.findById(id);
        produto.ativo = false;
        produto.persist();
    }
}
