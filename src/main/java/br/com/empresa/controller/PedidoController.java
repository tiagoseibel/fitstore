package br.com.empresa.controller;

import br.com.empresa.model.Pedido;
import br.com.empresa.model.Usuario;
import br.com.empresa.payload.PedidoPayload;
import br.com.empresa.service.PedidoService;
import br.com.empresa.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.SecurityContext;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/pedidos")
public class PedidoController {

    @Inject
    private PedidoService pedidoService;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private SecurityContext securityContext;

    /** public List<Pedido> pedidosUsuario() {
        return List.of();
    } */

    @GET
    @Path("/{id}")
    public RestResponse<Pedido> pedidoAberto(@PathParam("id") UUID uuid) {
        Optional<Pedido> oPedido = pedidoService.pedidoAberto(uuid);
        if (oPedido.isPresent()) {
            return RestResponse.ok(oPedido.get());
        } else {
            return RestResponse.notFound();
        }
    }

    @POST
    public void adicionarItem(PedidoPayload pedidoPayload) {
        pedidoService.adicionarItem(pedidoPayload);
    }

    public void removerItem() {

    }

    @POST
    @Path("/confirmar/{uuid}")
    public void confirmarPedido(@RestPath UUID uuid) {
        Usuario usuario = usuarioService.findByEmail(securityContext.getUserPrincipal().getName());
        pedidoService.confirmarPedido(uuid, usuario);
    }
}
