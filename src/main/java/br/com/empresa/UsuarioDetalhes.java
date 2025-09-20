package br.com.empresa;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record UsuarioDetalhes (Long id, String nomeCompleto) { }
