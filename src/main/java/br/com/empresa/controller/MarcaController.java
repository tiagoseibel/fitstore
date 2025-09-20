package br.com.empresa.controller;

import br.com.empresa.model.Marca;
import br.com.empresa.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("marcas")
public class MarcaController {

    @Inject
    private MarcaService marcaService;

    @GET
    public RestResponse<List<Marca>> listarTodas() {
        return RestResponse.ok(marcaService.listarTodas());
    }
}
