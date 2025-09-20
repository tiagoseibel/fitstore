package br.com.empresa.controller;

import br.com.empresa.model.Categoria;
import br.com.empresa.service.CategoriaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("categorias")
public class CategoriaController {

    @Inject
    private CategoriaService categoriaService;

    @GET
    public RestResponse<List<Categoria>> listarTodas() {
        return RestResponse.ok(categoriaService.listarTodas());
    }
}
