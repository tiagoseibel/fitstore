package br.com.empresa.controller;

import br.com.empresa.model.Produto;
import br.com.empresa.payload.Page;
import br.com.empresa.payload.PageRequest;
import br.com.empresa.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/produtos")
public class ProdutoController {

    @Inject
    private ProdutoService produtoService;

    @GET
    public List<Produto> listAll() {
        return produtoService.listarTodos();
    }

    @GET
    @Path("por-categoria/{idCategoria}")
    public RestResponse<Page> findByCategoria(@RestPath Long idCategoria, PageRequest pageRequest) {
        Page pagina = produtoService.findByCategoria(idCategoria, pageRequest);
        return RestResponse.ok(pagina);
    }

    @GET
    @Path("por-marca/{idMarca}")
    public RestResponse<Page> findByMarca(@RestPath Long idMarca, PageRequest pageRequest) {
        Page pagina = produtoService.findByMarca(idMarca, pageRequest);
        return RestResponse.ok(pagina);
    }

    @GET
    @Path("/{id}")
    public Produto findById(@PathParam("id") Long id) {
        return produtoService.findById(id);
    }

    @GET
    @Path("/{id}/image")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public RestResponse<?> getImage(@PathParam("id") Long id) {
        return RestResponse.ResponseBuilder.ok(produtoService.buscarFotoProduto(id))
                .header("Content-Disposition", "filename=produto.jpeg")
                .build();
    }
}
