package br.com.empresa.controller;

import br.com.empresa.model.Produto;
import br.com.empresa.payload.FotoProduto;
import br.com.empresa.payload.Page;
import br.com.empresa.payload.PageRequest;
import br.com.empresa.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Path("/produtos")
public class ProdutoController {

    @Inject
    private ProdutoService produtoService;

    @Inject
    S3Client s3Client;

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

    @POST
    public RestResponse salvar(Produto produto) {
        Produto newProduto = produtoService.save(produto);
        return RestResponse.ResponseBuilder.ok(newProduto).status(RestResponse.Status.CREATED).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload")
    public Response uploadFile(@RestForm("file") InputStream file) {
        String objectKey = "produtos/" + UUID.randomUUID().toString();

        try {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("Content-Type", "image/jpeg");

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket("ecommerce")
                    .key(objectKey)
                    .metadata(metadata)
                    .build();

            byte[] bytes = file.readAllBytes();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(bytes));

            FotoProduto fotoProduto = new FotoProduto();
            fotoProduto.uuid = objectKey;

            return Response.ok(fotoProduto).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro no upload: " + e.getMessage()).build();
        }
    }
}
