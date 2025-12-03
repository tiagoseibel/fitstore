package br.com.empresa.service;

import br.com.empresa.model.Produto;
import br.com.empresa.payload.Page;
import br.com.empresa.payload.PageRequest;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PathParam;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    S3Client s3Client;

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
        Produto produto = Produto.findById(id);

        GetObjectRequest gutObjectRequest = GetObjectRequest.builder()
                .bucket("ecommerce")
                .key(produto.imagem)
                .build();

        return s3Client.getObject(gutObjectRequest);
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
