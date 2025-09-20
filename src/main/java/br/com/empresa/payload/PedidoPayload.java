package br.com.empresa.payload;

import java.math.BigDecimal;
import java.util.UUID;

public record PedidoPayload(UUID uuid, Long idProduto, BigDecimal quantidade) {
}
